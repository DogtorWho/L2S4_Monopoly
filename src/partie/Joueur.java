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


public class Joueur {

	private String Nom;
	private int position;
	private int Argent;
	private int toursEnPrison; // compte le nombre de tours en prison
	private boolean Prisonnier;
	private boolean payerPrison;
	private boolean carteSortirPrison;
	private boolean bankrupt;
	private ArrayList<Case> casesPossedees = new ArrayList<Case>();
	private ArrayList<Carte> cartesPossedees = new ArrayList<Carte>();


	public Joueur(String Nom){
		setNom(Nom);
		setPosition(0);
		setArgent(120);
		setToursEnPrison(0);
		setPrisonnier(false);
		setPayerPrison(false);
		setCarteSortirPrison(false);
		setBankrupt(false);
		setCasesPossedees(casesPossedees);
		setCartesPossedees(cartesPossedees);
	}


	// === Fonctions de gestion des deplacements ===

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


	public void PayerPourSortirDePrison() throws PartieException {
		retirerArgent(50);
		setPrisonnier(false);
		teleporter(Plateau.getPlateau().trouverPositionCase("SimpleVisite"), false);
		setToursEnPrison(0);
	}

	public void SortirDePrisonApres3Tours() throws PartieException {
		setToursEnPrison(getToursEnPrison()+1);

		if(getToursEnPrison() > 3) {
			//Des.getDes().LancerDes();
			teleporter(Plateau.getPlateau().trouverPositionCase("SimpleVisite"), false);
			retirerArgent(50);
			setPrisonnier(false);
			setToursEnPrison(0);
		}
	}

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

	public void ajouterArgent(int Argent) {
		if(Argent < 0) {
			throw new IllegalArgumentException("L'argent est négatif");
		}
		setArgent(getArgent() + Argent);
	}

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

	public void ajouterCase(Case Case) {
		if(Case == null) {
			throw new IllegalArgumentException("La case est null");
		}
		casesPossedees.add(Case);
	}

	public void retirerCase(Case Case) {
		if(Case == null) {
			throw new IllegalArgumentException("La case est null");
		}
		casesPossedees.remove(Case);
	}


	// === Fonctions de gestion des cartes possedees ===

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

		public void ajouterCarte(Carte carte) {
			if(carte == null) {
				throw new IllegalArgumentException("La carte est null");
			}
			if(carte.toString().contains("Liberation")) {
				setCarteSortirPrison(true);
			}
			cartesPossedees.add(carte);
		}

		public void retirerCarte(Carte carte) {
			if(carte == null) {
				throw new IllegalArgumentException("La carte est null");
			}
			cartesPossedees.remove(carte);
		}

		public void utiliserCarte(Carte carte) throws PartieException {
			if(carte == null) {
				throw new IllegalArgumentException("La carte est null");
			}
			carte.appliquerEffets(this);
			retirerCarte(carte);
		}


	// === Fonctions de gestion des achats ===

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

	public int NombreDeMaison() {
		int nombre=0;
		for(Case proprietes : casesPossedees) {
			if(((TerrainConstructible)proprietes).getNombreMaison()<5) {
				nombre = nombre + ((TerrainConstructible)proprietes).getNombreMaison();
			}
		}
		return nombre;
	}

	public int NombreHotel() {
		int nombre=0;
		for(Case proprietes : casesPossedees) {
			nombre = nombre + ((TerrainConstructible)proprietes).getUnHotel();
		}
		return nombre;
	}

	public int nombreDeGaresPossedees() {
		int total = 0;

		for(Object Case : casesPossedees) {
			if(Case instanceof Gare) {
				total++;
			}
		}
		return total;
	}

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
