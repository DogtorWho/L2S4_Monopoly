package partie;


import java.util.ArrayList;
import cartes.Carte;
import cases.Case;
import cases.proprietes.Compagnie;
import cases.proprietes.Gare;
import cases.proprietes.Propriete;
import cases.proprietes.TerrainConstructible;
import partie.exceptions.BankruptException;
import partie.exceptions.PartieException;
import cases.CaseDepart;

/**
 * La classe Joueur stock toutes les données relatives a un joueur, qui sera ensuite ajouter a la liste des joueurs du plateau
 */
public class Joueur {

	/**
	 * String qui stock le noom du joueur
	 */
	private String Nom;
	/**
	 * entier qui stock la position du joueur sur le plateau
	 */
	private int position;
	/**
	 * entier qui stock l'argent du joueur
	 */
	private int Argent;
	/**
	 * entier qui stock le nombre de tours en prison
	 * lorsque le joueur va en prison il devra attendre 3 tours avant de pouvor sortir
	 */
	private int toursEnPrison;
	/**
	 * boolean qui est vrai quand le joueur est prisonnier
	 */
	private boolean Prisonnier;
	/**
	 * boolean qui est vrai quand le joueur choisi de payer pour sortir de prison
	 */
	private boolean payerPrison;
	/**
	 * boolean qui est vrai quand le joueur possede une carte pour sortir de prison
	 */
	private boolean carteSortirPrison;
	/**
	 * boolean qui est vrai quand le joueur est en bankrupt (perdu la partie)
	 */
	private boolean bankrupt;
	/**
	 * liste qui stock les cases possedees du joueur
	 */
	private ArrayList<Case> casesPossedees = new ArrayList<Case>();
	/**
	 * liste qui stock les cartes possedees du joueur
	 */
	private ArrayList<Carte> cartesPossedees = new ArrayList<Carte>();


	public Joueur(String Nom){
		setNom(Nom);
		setPosition(0);
		setArgent(1500);
		setToursEnPrison(0);
		setPrisonnier(false);
		setPayerPrison(false);
		setCarteSortirPrison(false);
		setBankrupt(false);
		setCasesPossedees(casesPossedees);
		setCartesPossedees(cartesPossedees);
	}


	// === Fonctions de gestion des deplacements ===
	/**
	 * <p>Methode qui change la position du joueur vers l'avant en fonction du resultat du lancer de Des</p>
	 * 
	 * @param nombresCases le nombre de cases a avancer le joueur 
	 * @throws PartieException si le deplacement n'est pas entre 2 et 12, car on lance 2 dés a 6 faces
	 */
	public void avancer(int nombresCases) throws PartieException {
		if(nombresCases < 2 || nombresCases > 12) {
			throw new PartieException("Le déplacement n'est pas possible");
		}
		else {
			int caseArrivee = getPosition() + nombresCases;
			if(caseArrivee <= Plateau.getPlateau().getDerniereCase()) {
				setPosition(caseArrivee);
			}
			else {
				caseArrivee = caseArrivee - Plateau.getPlateau().getDerniereCase() - 1; // -1 car la case depart est a 0
				setPosition(caseArrivee);
				if(caseArrivee != 0) { // si on est pas sur la case depart, sinon on double le gain
					Plateau.getPlateau().getCase(0).appliquerEffets(this);
				}
			}
			Plateau.getPlateau().getCase(caseArrivee).appliquerEffets(this);
		}
	}
 
	/**
	 * <p>Methode qui change la position du joueur vers l'arriere en fonction de nombreCases</p>
	 * <p>Utile pour les cartes chance ou communaute qui nous demande de reculer</p>
	 * 
	 * @param nombresCases le nombre de cases a reculer le joueur 
	 * @throws PartieException 
	 */
	public void reculer(int nombresCases) throws PartieException {
		int caseArrivee = getPosition() - nombresCases;
		if(caseArrivee >= 0) {
			setPosition(caseArrivee);
		}
		else {
			caseArrivee = Plateau.getPlateau().getDerniereCase() + caseArrivee + 1;
			setPosition(caseArrivee);
		}
		Plateau.getPlateau().getCase(caseArrivee).appliquerEffets(this);
	}

	/**
	 * <p>Methode qui teleporte le joueur a une case donnée, avec possibilité de gagner de l'argent si il passe par la case depart</p>
	 * <p>Utile pour avancer de moins de 2 cases ou plus de 12 cases</p>
	 * 
	 * @param caseArrivee la case ou le joueur sera teleporté
	 * @param gainCaseDepart vrai si l'on veut que le joueur gagne de l'argent en faisant un tour (utile quand on teleporte le joueur en prison sans qu'il gagne d'argent)
	 * @throws PartieException
	 */
	public void teleporter(int caseArrivee, boolean gainCaseDepart) throws PartieException {
		if(caseArrivee < 0) {
			throw new IllegalArgumentException("Le numéro de case est négatif");
		}
		if(caseArrivee < getPosition() && gainCaseDepart) { // si la case d'arrivée est avant le joueur, il devra faire le tour du plateau et donc passer par la case depart
			ajouterArgent(((CaseDepart) Plateau.getPlateau().getCase(0)).getGainTour());
		}
		setPosition(caseArrivee);
		Plateau.getPlateau().getCase(caseArrivee).appliquerEffets(this);
	}

	/**
	 * <p>Methode qui deplace le joueur de la prison vers SimpleVisite, en lui retirant de l'argent</p>
	 * 
	 * @throws PartieException
	 */
	public void PayerPourSortirDePrison() throws PartieException {
		retirerArgent(50);
		setPrisonnier(false);
		teleporter(Plateau.getPlateau().trouverPositionCase("SimpleVisite"), false);
		setToursEnPrison(0);
	}

	/**
	 * <p>Methode qui incremente le nombre de tours en prison du joueur</p>
	 * <p>Si le joueur est en prison depuis 3 tours il se fait deplacer vers SimpleVisite et on lui retire de l'argent</p>
	 * 
	 * @throws PartieException
	 */
	public void SortirDePrisonApres3Tours() throws PartieException {
		setToursEnPrison(getToursEnPrison()+1);

		if(getToursEnPrison() > 3) {
			teleporter(Plateau.getPlateau().trouverPositionCase("SimpleVisite"), false);
			retirerArgent(50);
			setPrisonnier(false);
			setToursEnPrison(0);
		}
	}

	/**
	 * <p>Methode qui lance les dés, si le joueur fait un double il sort de prison gratuitement</p>
	 * 
	 * @return vrai si le joueur a fait un double, et donc est sorti de prison
	 * @throws PartieException
	 */
	public boolean TenterDeSortirDePrison() throws PartieException {
		Des.getDes().LancerDes();
		if(Des.getDes().isDouble()) {
			setPrisonnier(false);
			teleporter(Plateau.getPlateau().trouverPositionCase("SimpleVisite") + Des.getDes().getSomme(), false);
			setToursEnPrison(0);
			System.out.println("Double : sortie");
			return true;
		}
		return false;
	}


	// === Fonctions de gestion de l'argent ===
	/**
	 * <p>Methode qui ajoute de l'argent au joueur</p>
	 * 
	 * @param Argent l'argent a ajouter au joueur
	 */
	public void ajouterArgent(int Argent) {
		if(Argent < 0) {
			throw new IllegalArgumentException("L'argent est négatif");
		}
		setArgent(getArgent() + Argent);
	}

	/**
	 * <p>Methode qui retire de l'argent au joueur</p>
	 * 
	 * @param Argent l'argent a retirer au joueur
	 * @throws BankruptException si le joueur tombe en dessous de 0 argent
	 */
	public void retirerArgent(int Argent) throws BankruptException {
		if(Argent < 0) {
			throw new IllegalArgumentException("L'argent est négatif");
		}
		if(getArgent() - Argent >= 0) {
			setArgent(getArgent() - Argent);
		}
		else {
			setArgent(0);
			throw new BankruptException(this, "Vous n'avez plus d'argent");
		}
	}


	// === Fonctions de gestion des cases possedees ===
	/**
	 * <p>Methode qui affiche les cases du joueur dans l'ordre de la liste des cases possedees</p>
	 */
	public void afficherCasesJoueur() {
		if(getCasesPossedees() == null) {
			throw new IllegalArgumentException("La liste de cases possedees est null");
		}
		if(getCasesPossedees().isEmpty()) {
			System.out.println("Aucune case");
		}
		else {
			for(Object Case : casesPossedees) {
				System.out.println(Case.toString());
			}
		}
	}
	
	/**
	 * <p>Methode qui ajoute une case a la fin de la liste des cases possedees</p>
	 * 
	 * @param Case la case a ajouter
	 */
	public void ajouterCase(Case Case) {
		if(Case == null) {
			throw new IllegalArgumentException("La case est null");
		}
		casesPossedees.add(Case);
	}

	/**
	 * <p>Methode qui retire une case de la liste des cases possedees</p>
	 * 
	 * @param Case la case a retirer
	 */
	public void retirerCase(Case Case) {
		if(Case == null) {
			throw new IllegalArgumentException("La case est null");
		}
		casesPossedees.remove(Case);
	}


	// === Fonctions de gestion des cartes possedees ===
	/**
	 * <p>Methode qui affiche les cartes du joueur dans l'ordre de la liste des cartes possedees</p>
	 */
		public void afficherCartesJoueur() {
			if(getCartesPossedees() == null) {
				throw new IllegalArgumentException("La liste de cartes possedees est null");
			}
			if(getCartesPossedees().isEmpty()) {
				System.out.println("Aucune carte");
			}
			else {
				for(Object Carte : cartesPossedees) {
					System.out.println(Carte.toString());
				}
			}
		}

		/**
		 * <<p>Methode qui ajoute une cartes a la fin de la liste des cartes possedees</p>
		 * 
		 * @param carte la carte a ajouter
		 */
		public void ajouterCarte(Carte carte) {
			if(carte == null) {
				throw new IllegalArgumentException("La carte est null");
			}
			if(carte.toString().contains("Liberation")) {
				setCarteSortirPrison(true);
			}
			cartesPossedees.add(carte);
		}

		/**
		 * <p>Methode qui retire une carte de la liste des carte possedees</p>
		 * 
		 * @param carte la carte a retirer
		 */
		public void retirerCarte(Carte carte) {
			if(carte == null) {
				throw new IllegalArgumentException("La carte est null");
			}
			cartesPossedees.remove(carte);
		}

		/**
		 * <p>Methode qui utilise une carte de la liste des carte possedees</p>
		 * <p>Applique l'effet de la carte puis la retire de la liste du joueur</p>
		 * 
		 * @param carte la carte a utiliser
		 * @throws PartieException
		 */
		public void utiliserCarte(Carte carte) throws PartieException {
			if(carte == null) {
				throw new IllegalArgumentException("La carte est null");
			}
			carte.appliquerEffets(this);
			retirerCarte(carte);
		}


	// === Fonctions de gestion des achats ===
		/**
		 * <p>Methode qui achete une propriete et la stock dans la liste de cases possedees</p>
		 * 
		 * @param propriete la propriete a acheter
		 * @throws PartieException si la propriete est deja possedee ou que le joueur n'a pas assez d'argent
		 */
	public void acheterPropriete(Propriete propriete) throws PartieException {
		if(propriete == null) {
			throw new IllegalArgumentException("La propriete est null");
		}
		if(propriete.getProprietaire() != null) {
			throw new PartieException("La propriete est deja possedee");
		}
		else {
			if(propriete.getPrixAchat() <= getArgent()) {
				retirerArgent(propriete.getPrixAchat());
				ajouterCase(propriete);
				propriete.setProprietaire(this);
			}
			else {
				throw new PartieException("Pas assez d'argent");
			}
		}
	}

	/**
	 * <p>Methode qui permet de savoir si le joueur possede tous les terrains d'une couleur donnée</p>
	 * <p>Utile lors de l'achat d'une maison, car le joueur doit possedee tous les terrains de la couleur ou la maison sera posée</p>
	 * 
	 * @param couleur la couleur des proprietes a chercher
	 * @return vrai si le joueur possede toutes les cases de la couleur donnée
	 */
	public boolean PossedeToutesLesMemesCouleurs(String couleur) {
		if(couleur == null || couleur.trim().isEmpty()){
			throw new IllegalArgumentException("Couleur vide ou null");
		}
		int total = 0;

		for(Object Case : casesPossedees) {
			if(Case instanceof TerrainConstructible) {
				if(((TerrainConstructible) Case).getCouleur().contentEquals(couleur)) {
					total++;
				}
			}
		}
		if(couleur.contentEquals("MARRON") || couleur.contentEquals("BLEU FONCE")) {
			if(total == 2) {
				return true;
			}
		}
		else {
			if(total == 3) {
				return true;
			}
		}
		return false;
	}

	/**
	 * <p>Methode qui permet de savoir si la maison peut etre posée sur le terrain de la couleur donnée</p>
	 * 
	 * @param couleur la couleur des proprietes a chercher
	 * @return vrai si la maison peut etre posée en fonction du nombre de maisons sur les autres terrains de meme couleur
	 */
	private boolean MaisonPoseeValide(String couleur) {
		if(couleur == null || couleur.trim().isEmpty()){
			throw new IllegalArgumentException("Couleur vide ou null");
		}
		int min = -1;
		int max = -1;

		for(Object Case : casesPossedees) {
			if(Case instanceof TerrainConstructible) {
				if(((TerrainConstructible) Case).getCouleur().contentEquals(couleur)) {
					if(min == -1 && max == -1) {
						min = ((TerrainConstructible) Case).getNombreMaison();
						max = ((TerrainConstructible) Case).getNombreMaison();
					}
					else if(((TerrainConstructible) Case).getNombreMaison() < min) {
						min = ((TerrainConstructible) Case).getNombreMaison();
					}
					else if(((TerrainConstructible) Case).getNombreMaison() > max) {
						max = ((TerrainConstructible) Case).getNombreMaison();
					}
				}
			}
		}
		if(max - min <= 1) {
			return true;
		}

		return false;
	}

	/**
	 * <p>Methode qui achete et pose une maison sur un terrain donné</p>
	 * 
	 * @param terrain le terrain ou l'on ajoute une maison
	 * @throws PartieException si le joueur ne possede pas le terrain, si le nombre de maisons sur les autres terrains est trop grand, 
	 * si le joueur n'a pas assez d'argent, si il ne peut plus y avoir de maisons, si le joueur n'a pas tous les terrains de la couleur du terrain donné
	 */
	public void acheterMaison(TerrainConstructible terrain) throws PartieException {
		if(terrain == null) {
			throw new IllegalArgumentException("Le terrain est null");
		}
		if(terrain.getProprietaire() != this) {
			throw new PartieException("Vous ne possedez pas ce terrain");
		}

		if(PossedeToutesLesMemesCouleurs(terrain.getCouleur())) {
			if(terrain.getNombreMaison() < 5) {
				if(terrain.getPrixMaison() <= getArgent()) {
					terrain.setNombreMaison(terrain.getNombreMaison() + 1);
					if(MaisonPoseeValide(terrain.getCouleur())) {
						retirerArgent(terrain.getPrixMaison());
					}
					else {
						terrain.setNombreMaison(terrain.getNombreMaison() - 1);
						throw new PartieException("Il y'a deja trop de maisons par rapport aux autres terrains " + terrain.getCouleur());
					}
				}
				else {
					throw new PartieException("Pas assez d'argent");
				}
			}
			else {
				throw new PartieException("Le nombre maximum de maisons est deja atteint");
			}
		}
		else {
			throw new PartieException("Vous ne possedez pas tous les terrains " + terrain.getCouleur());
		}
	}

	/**
	 * <p>Methode qui compte le nombre de maisons sur tous les terrains du joueur</p>
	 * <p>Utile pour les taxes sur le nombre de maisons d'un joueur</p>
	 * 
	 * @return le nombre de maisons total du joueur
	 */
	public int NombreDeMaison() {
		int nombre=0;
		for(Case proprietes : casesPossedees) {
			if(((TerrainConstructible)proprietes).getNombreMaison()<5) {
				nombre = nombre + ((TerrainConstructible)proprietes).getNombreMaison();
			}
		}
		return nombre;
	}

	/**
	 * <p>Methode qui compte le nombre d'hotels sur tous les terrains du joueur</p>
	 * <p>Utile pour les taxes sur le nombre d'hotels d'un joueur</p>
	 * 
	 * @return le nombre d'hotels total du joueur
	 */
	public int NombreHotel() {
		int nombre=0;
		for(Case proprietes : casesPossedees) {
			nombre = nombre + ((TerrainConstructible)proprietes).getUnHotel();
		}
		return nombre;
	}

	/**
	 * <p>Methode qui compte le nombre de gares du joueur</p>
	 * <p>Utile pour le loyer qui est calculé en fonction du nombre de gares</p>
	 * 
	 * @return le nombre de gares total du joueur
	 */
	public int nombreDeGaresPossedees() {
		int total = 0;

		for(Object Case : casesPossedees) {
			if(Case instanceof Gare) {
				total++;
			}
		}
		return total;
	}

	/**
	 * <p>Methode qui compte le nombre de compagnies du joueur</p>
	 * <p>Utile pour le loyer qui est calculé en fonction du nombre de compagnies</p>
	 * 
	 * @return vrai si le joueur a toutes les compagnies
	 */
	public boolean PossedeToutesLesCompagnies() {
		int total = 0;

		for(Object Case : casesPossedees) {
			if(Case instanceof Compagnie) {
				total++;
			}
		}
		if(total == 2) {
			return true;
		}
		return false;
	}


	// === Gestion bankrupt ===
	/**
	 * <p>Methode qui est lancée lors d'une BankruptException</p>
	 * <p>Elle retire le joueur de la partie en redistribuant ses cases et cartes</p>
	 */
	public void bankrupt() {
		if( ! isBankrupt()) {
			setBankrupt(true);
			setArgent(0);

			for(Carte Carte : getCartesPossedees()) {
				if(Carte.isPaquetChance()) {
					Plateau.getPlateau().MettreCarteChanceAuFond(Carte);
				}
				else {
					Plateau.getPlateau().MettreCarteCommunauteAuFond(Carte);
				}
			}
			getCartesPossedees().clear();

			for(Case Case : getCasesPossedees()) {
				Plateau.getPlateau().getCase(Case.getPosition()).setProprietaire(null);
				if(Case instanceof TerrainConstructible) {
					((TerrainConstructible) Plateau.getPlateau().getCase(Case.getPosition())).setNombreMaison(0);
				}
			}
			getCasesPossedees().clear();
		}
	}



	// === Getters / Setters ===

	public String getNom() {
		return Nom;
	}
	public void setNom(String nom) {
		if(nom == null || nom.trim().isEmpty()){
			throw new IllegalArgumentException("Nom vide ou null");
		}
		Nom = nom;
	}
	public int getArgent() {
		return Argent;
	}
	public void setArgent(int argent) {
		if(argent < 0){
			throw new IllegalArgumentException("L'argent est négatif");
		}
		Argent = argent;
	}
	public boolean isPrisonnier() {
		return Prisonnier;
	}
	public void setPrisonnier(boolean prisonnier) {
		Prisonnier = prisonnier;
	}
	public ArrayList<Case> getCasesPossedees() {
		return casesPossedees;
	}
	public void setCasesPossedees(ArrayList<Case> casesPossedees) {
		this.casesPossedees = casesPossedees;
	}
	public ArrayList<Carte> getCartesPossedees() {
		return cartesPossedees;
	}
	public void setCartesPossedees(ArrayList<Carte> cartesPossedees) {
		this.cartesPossedees = cartesPossedees;
	}
	public int getPosition() {
		return position;
	}
	public void setPosition(int position) {
		if(position < 0){
			throw new IllegalArgumentException("La position est négative");
		}
		this.position = position;
	}
	public int getToursEnPrison() {
		return toursEnPrison;
	}
	public void setToursEnPrison(int toursEnPrison) {
		this.toursEnPrison = toursEnPrison;
	}
	public boolean isPayerPrison() {
		return payerPrison;
	}
	public void setPayerPrison(boolean payerPrison) {
		this.payerPrison = payerPrison;
	}
	public boolean isCarteSortirPrison() {
		return carteSortirPrison;
	}
	public void setCarteSortirPrison(boolean carteSortirPrison) {
		this.carteSortirPrison = carteSortirPrison;
	}
	public boolean isBankrupt() {
		return bankrupt;
	}
	public void setBankrupt(boolean bankrupt) {
		this.bankrupt = bankrupt;
	}
}
