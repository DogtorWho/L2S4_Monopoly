package partie;

import java.util.ArrayList;
import java.util.List;

import cartes.Carte;
import cases.Case;
import partie.exceptions.PartieException;
import partie.fichiers.Fichier;
import partie.parser.Parser;
import partie.parser.parserCoordonnees;
import partie.parser.parserCartesChance.ParserDeplacementChance;
import partie.parser.parserCartesChance.ParserEncaisserChance;
import partie.parser.parserCartesChance.ParserFraisChance;
import partie.parser.parserCartesChance.ParserLiberationChance;
import partie.parser.parserCartesChance.ParserPayerChance;
import partie.parser.parserCartesCommunaute.ParserAnniversaireCommunaute;
import partie.parser.parserCartesCommunaute.ParserPiocherChanceCommunaute;
import partie.parser.parserCartesCommunaute.ParserDeplacementCommunaute;
import partie.parser.parserCartesCommunaute.ParserEncaisserCommunaute;
import partie.parser.parserCartesCommunaute.ParserFraisCommunaute;
import partie.parser.parserCartesCommunaute.ParserLiberationCommunaute;
import partie.parser.parserCartesCommunaute.ParserPayerCommunaute;
import partie.parser.parserCases.ParserAllerEnPrison;
import partie.parser.parserCases.ParserCaisseCommunaute;
import partie.parser.parserCases.ParserCaseDepart;
import partie.parser.parserCases.ParserChance;
import partie.parser.parserCases.ParserCompagnie;
import partie.parser.parserCases.ParserGare;
import partie.parser.parserCases.ParserImpotSurLeRevenu;
import partie.parser.parserCases.ParserParkingGratuit;
import partie.parser.parserCases.ParserPrison;
import partie.parser.parserCases.ParserSimpleVisite;
import partie.parser.parserCases.ParserTaxeDeLuxe;
import partie.parser.parserCases.ParserTerrainConstructible;

/**
 * La classe Plateau gère le plateau du Monopoly : cases, cartes chances et communaute, joueurs,...
 * On utilise un type Singleton pour avoir access aux plateau dans toutes les autres classes
 * 
 * @author doctorwho
 *
 */
public class Plateau {
	
	/**
	 * instance du Singleton Plateau
	 */
	private static Plateau instance = null;	
	/**
	 * entier qui stock le nombre de joueurs encore en vie sur le plateau
	 */
	private int nbJoueurs = 0;
	/**
	 * entier qui stock la position du joueur actif dans la liste de joueurs
	 */
	private int indexJoueurActif = 0;
	/**
	 * entier qui stock la position de la derniere case du plateau
	 */
	private int DerniereCase;
	
	// ===== Fichiers =====
	/**
	 * String qui stock le chemin du fichier contenant les cases du plateau a parser
	 */
	private static String nomDuFichierPlateau = "Data/Terrains.csv";
	/**
	 * String qui stock le chemin du fichier contenant les cartes chances a parser
	 */
	private static String nomDuFichierCartesChances = "Data/CartesChance.csv";
	/**
	 * String qui stock le chemin du fichier contenant les cartes communaute a parser
	 */
	private static String nomDuFichierCartesCommunaute = "Data/CartesCommunaute.csv";
	/**
	 * String qui stock le chemin du fichier contenant les coordonnees des cases du plateau a parser
	 */
	private static String nomDuFichierCoordonnees = "Data/CoordonneesCases.csv";
	
	// ===== Parsers =====
	/**
	 * Parser du fichier contenant les cases du plateau
	 */
	private Parser premierParserPlateau = null;
	/**
	 * Parser du fichier contenant les cartes chances
	 */
	private Parser premierParserCartesChances = null;
	/**
	 * Parser du fichier contenant les cartes communaute
	 */
	private Parser premierParserCartesCommunaute = null;
	/**
	 * Parser du fichier contenant les coordonnees des cases du plateau
	 */
	private Parser premierParserCoordonnees = null;
	
	// ===== Listes =====
	/**
	 * liste qui stock les joueurs encore en vie sur le plateau
	 */
	private List<Joueur> listeJoueurs = new ArrayList<Joueur>();
	/**
	 * liste qui stock les cases du plateau
	 */
	private List<Case> listeCases = new ArrayList<Case>();
	/**
	 * liste qui stock les cartes chance
	 */
	private List<Carte> listeCartesChances = new ArrayList<Carte>();
	/**
	 * liste qui stock les caretes communaute
	 */
	private List<Carte> listeCartesCommunaute = new ArrayList<Carte>();
	/**
	 * liste qui stock les coordonnees des cases du plateau
	 */
	private List<Coordonnees> listeCoordonnees = new ArrayList<Coordonnees>();
	
	
		
	private Plateau(){		
		// == Initialisation des parser du plateau ==
		premierParserPlateau = new ParserCaseDepart(premierParserPlateau);
		premierParserPlateau = new ParserGare(premierParserPlateau);
		premierParserPlateau = new ParserCaisseCommunaute(premierParserPlateau);
		premierParserPlateau = new ParserTerrainConstructible(premierParserPlateau);
		premierParserPlateau = new ParserImpotSurLeRevenu(premierParserPlateau);
		premierParserPlateau = new ParserChance(premierParserPlateau);
		premierParserPlateau = new ParserSimpleVisite(premierParserPlateau);
		premierParserPlateau = new ParserCompagnie(premierParserPlateau);
		premierParserPlateau = new ParserParkingGratuit(premierParserPlateau);
		premierParserPlateau = new ParserAllerEnPrison(premierParserPlateau);
		premierParserPlateau = new ParserTaxeDeLuxe(premierParserPlateau);
		premierParserPlateau = new ParserPrison(premierParserPlateau);
		
		// == Initialisation des parser des cartes chances ==
		premierParserCartesChances = new ParserDeplacementChance(premierParserCartesChances);
		premierParserCartesChances = new ParserPayerChance(premierParserCartesChances);
		premierParserCartesChances = new ParserEncaisserChance(premierParserCartesChances);
		premierParserCartesChances = new ParserFraisChance(premierParserCartesChances);
		premierParserCartesChances = new ParserLiberationChance(premierParserCartesChances);
		
		// == Initialisation des parser des cartes communaute ==
		premierParserCartesCommunaute = new ParserDeplacementCommunaute(premierParserCartesCommunaute);
		premierParserCartesCommunaute = new ParserPayerCommunaute(premierParserCartesCommunaute);
		premierParserCartesCommunaute = new ParserEncaisserCommunaute(premierParserCartesCommunaute);
		premierParserCartesCommunaute = new ParserFraisCommunaute(premierParserCartesCommunaute);
		premierParserCartesCommunaute = new ParserLiberationCommunaute(premierParserCartesCommunaute);
		premierParserCartesCommunaute = new ParserAnniversaireCommunaute(premierParserCartesCommunaute);
		premierParserCartesCommunaute = new ParserPiocherChanceCommunaute(premierParserCartesCommunaute);
		
		// == Initialisation du parser des coordonnees ==
		premierParserCoordonnees = new parserCoordonnees(premierParserCoordonnees);
	}
	
	
	// === Fonctions de gestion du plateau ===
	/**
	 * <p>Methode qui sert a recuperer l'instance du Singleton Plateau dans les autres classes</p>
	 * 
	 * @return l'instance de Plateau
	 */
	public static Plateau getPlateau() {
		if(instance == null) {
			instance = new Plateau();
		}
		return instance;
	}
			
	/**
	 * <p>Methode qui initialise les cases du plateau en appelant le parseur du fichier les contenant</p>
	 * <p>Appel aussi les methodes d'inititialisation des cartes chance, communaute et des coordonnees</p>
	 */
	public void initialiserPlateau() {
		try {
			listeCases.clear();
			listeCartesChances.clear();
			listeCartesCommunaute.clear();
			listeCoordonnees.clear();
			
			Fichier.lire(nomDuFichierPlateau, premierParserPlateau);
			setDerniereCase(((Case) getListeCases().get(getListeCases().size() - 2)).getPosition());
			initialiserCartesChances();
			initialiserCartesCommunaute();
			initialiserCoordonnees();
		}
		catch (IllegalArgumentException e) {
			System.err.println(e.getMessage());
		}
	}
	
	/**
	 * <p>Methode qui affiche les cases du plateau dans l'ordre de la liste de cases</p>
	 */
	public void afficherPlateau() {	
		if(getListeCases() == null) {
			throw new IllegalArgumentException("La liste est null");
		}
		for(Object Case : listeCases) {
			System.out.println(Case.toString());
		}
		System.out.println("");
	}
	
	
	// === Fonctions de gestion des cases ===
	/**
	 * <p>Methode qui ajoute une case a la fin de la liste de cases</p>
	 * 
	 * @param Case la case a ajouter
	 */
	public void ajouterCase(Case Case) {	
		if(getListeCases() == null) {
			throw new IllegalArgumentException("La liste est null");
		}
		if(Case == null) {
			throw new IllegalArgumentException("La case a ajouter est null");
		}
		getListeCases().add(Case);
	}
	
	/**
	 * <p>Methode qui cherche et renvoi une case de la liste de cases en fonction de la position donnée</p>
	 * 
	 * @param numeroCase la position de la case a chercher
	 * @return la case a la position numeroCase de la liste de cases
	 */
	public Case getCase(int numeroCase) {
		if(getListeCases() == null) {
			throw new IllegalArgumentException("La liste est null");
		}
		if(numeroCase < 0) {
			throw new IllegalArgumentException("Le numéro de case est négatif ou n'a pas ete trouvé");
		}
		return (Case) (getListeCases().get(numeroCase));
	}
	
	/**
	 * <p>Methode qui cherche et affiche une case de la liste de cases en fonction de la position donnée</p>
	 * 
	 * @param numeroCase la position de la case a chercher
	 */
	public void afficherUneCase(int numeroCase) {
		if(getListeCases() == null) {
			throw new IllegalArgumentException("La liste est null");
		}
		if(numeroCase < 0) {
			throw new IllegalArgumentException("Le numéro de case est négatif");
		}
		System.out.println(getCase(numeroCase).toString());
	}
	
	/**
	 * <p>Methode qui cherche la position d'une case de la liste de cases en fonction du nom donné</p>
	 * 
	 * @param nomCase le nom de la case a chercher
	 * @return la position de la case trouvée ou -1 sinon
	 */
	public int trouverPositionCase(String nomCase) {	
		if(getListeCases() == null) {
			throw new IllegalArgumentException("La liste est null");
		}
		if(nomCase == null || nomCase.trim().isEmpty()){
			throw new IllegalArgumentException("Nom de la case vide ou null");
		}
		for(Object Case : listeCases) {
			if(Case.toString().contains(nomCase)) {
				return (((Case) Case).getPosition());
			}
		}
		return -1;
	}
	
	
	// === Fonctions de gestion des joueurs ===	
	/**
	 * <p>Methode qui ajoute un joueur a la liste de joueurs et augemente le nombre de joueur de 1</p>
	 * 
	 * @param joueur le joueur a ajouter
	 */
	public void ajouterJoueur(Joueur joueur) {
		if(joueur == null) {
			throw new IllegalArgumentException("Le joueur a ajouter est null");
		}
		setNbJoueurs(getNbJoueurs() + 1);	
		getListeJoueurs().add(joueur);
	}
	
	
	// === Fonctions de gestion des cartes chance ===
	/**
	 * <p>Methode qui initialise les cartes chance en appelant le parseur du fichier les contenant</p>
	 */
	public void initialiserCartesChances() {
		try {
			Fichier.lire(nomDuFichierCartesChances, premierParserCartesChances);
		}
		catch (IllegalArgumentException e) {
			System.err.println(e.getMessage());
		}
	}
	
	/**
	 * <p>Methode qui affiche les cartes chance dans l'ordre de la liste de cartes chances</p>
	 */
	public void afficherCartesChances() {	
		if(getListeCartesChances() == null) {
			throw new IllegalArgumentException("La liste est null");
		}
		for(Object Carte : listeCartesChances) {
			System.out.println(Carte.toString());
		}
	}
	
	/**
	 * <p>Methode qui ajoute une carte chance a la fin de la liste de cartes chances</p>
	 * 
	 * @param CarteChance la carte chance a ajouter
	 */
	public void ajouterCarteChance(Carte CarteChance) {	
		if(getListeCartesChances() == null) {
			throw new IllegalArgumentException("La liste est null");
		}
		if(CarteChance == null) {
			throw new IllegalArgumentException("La carte chance a ajouter est null");
		}
		listeCartesChances.add(CarteChance);
	}
	
	/**
	 * <p>Methode qui met une carte chance spécifique au dessus du paquet (au debut de la liste)</p>
	 * <p>Deux posibilités : </p>
	 * <p> - La carte est deja dans le paquet, on la supprime et on l'ajoute au debut</p>
	 * <p> - La carte n'est pas dans le paquet, on l'ajoute au debut</p>
	 * 
	 * @param CarteChance la carte chance a mettre au dessus
	 */
	public void MettreCarteChanceAuDessus(Carte CarteChance) {
		if(getListeCartesChances() == null) {
			throw new IllegalArgumentException("La liste est null");
		}
		if(CarteChance == null) {
			throw new IllegalArgumentException("La carte est null");
		}
		if(trouverCarteChance(CarteChance.getMessage()) == null) {
			listeCartesChances.add(0, CarteChance);
		}
		else { // la carte est deja dans le paquet
			for(Object Carte : listeCartesChances) {
				if(Carte.toString().contains(CarteChance.getMessage())) {
					listeCartesChances.remove(Carte);
					listeCartesChances.add(0, CarteChance);
					return;
				}
			}
		}
	}
	
	/**
	 * <p>Methode qui met une carte chance spécifique a la fin du paquet (a la fin de la liste)</p>
	 * <p>Deux posibilités : </p>
	 * <p> - La carte est deja dans le paquet, on la supprime et on l'ajoute a la fin</p>
	 * <p> - La carte n'est pas dans le paquet, on l'ajoute a la fin</p>
	 * 
	 * @param CarteChance la carte chance a mettre a la fin
	 */
	public void MettreCarteChanceAuFond(Carte CarteChance) {
		if(getListeCartesChances() == null) {
			throw new IllegalArgumentException("La liste est null");
		}
		if(CarteChance == null) {
			throw new IllegalArgumentException("La carte est null");
		}
		if(trouverCarteChance(CarteChance.getMessage()) == null) {
			ajouterCarteChance(CarteChance);
		}
		else { // la carte est deja dans le paquet
			for(Object Carte : listeCartesChances) {
				if(Carte.toString().contains(CarteChance.getMessage())) {
					listeCartesChances.remove(Carte);
					ajouterCarteChance(CarteChance);
					return;
				}
			}
		}
	}
	
	/**
	 * <p>Methode qui tire la premiere carte chance du paquet</p>
	 * <p>Deux possibilités : </p>
	 * <p> - l'effet de la carte est immédiat, on applique l'effet sur le joueur et on remet la carte au fond du paquet</p>
	 * <p> - l'effet de la carte n'est pas immédiat, le joueur garde la carte</p>
	 * 
	 * @param joueur le joueur qui tire une carte chance
	 * @throws PartieException 
	 */
	public void TirerUneCarteChance(Joueur joueur) throws PartieException {
		if(joueur == null) {
			throw new IllegalArgumentException("Le joueur est null");
		}
		Carte CarteTiree = (Carte) listeCartesChances.get(0);	
		if(CarteTiree.isEffetImmediat()) {
			CarteTiree.appliquerEffets(joueur);
			MettreCarteChanceAuFond(CarteTiree);
		}
		else {
			joueur.ajouterCarte(CarteTiree);
			getListeCartesChances().remove(0);
		}	
	}
	
	/**
	 * <p>Methode qui cherche une carte chance dans la liste de cartes chance en fonction de son message</p>
	 * 
	 * @param message le message de la carte a trouver
	 * @return la carte trouvée ou null sinon
	 */
	public Carte trouverCarteChance(String message) {	
		if(getListeCartesChances() == null) {
			throw new IllegalArgumentException("La liste est null");
		}
		if(message == null || message.trim().isEmpty()){
			throw new IllegalArgumentException("Message vide ou null");
		}
		for(Object Carte : listeCartesChances) {
			if(Carte.toString().contains(message)) {
				return (Carte) Carte;
			}
		}
		return null;
	}
	
	
	// === Fonctions de gestion des cartes communaute ===
	/**
	 * <p>Methode qui initialise les cartes communaute en appelant le parseur du fichier les contenant</p>
	 */
	public void initialiserCartesCommunaute() {
		try {
			Fichier.lire(nomDuFichierCartesCommunaute, premierParserCartesCommunaute);
		}
		catch (IllegalArgumentException e) {
			System.err.println(e.getMessage());
		}
	}
	
	/**
	 * <p>Methode qui affiche les cartes communaute dans l'ordre de la liste de cartes communaute</p>
	 */
	public void afficherCartesCommunaute() {	
		if(getListeCartesCommunaute() == null) {
			throw new IllegalArgumentException("La liste est null");
		}
		for(Object Carte : listeCartesCommunaute) {
			System.out.println(Carte.toString());
		}
	}
	
	/**
	 * <p>Methode qui ajoute une carte communaute a la fin de la liste de cartes communaute</p>
	 * 
	 * @param CarteCommunaute la carte communaute a ajouter
	 */
	public void ajouterCarteCommunaute(Carte CarteCommunaute) {	
		if(getListeCartesCommunaute() == null) {
			throw new IllegalArgumentException("La liste est null");
		}
		if(CarteCommunaute == null) {
			throw new IllegalArgumentException("La carte communaute a ajouter est null");
		}
		listeCartesCommunaute.add(CarteCommunaute);
	}
	
	/**
	 * <p>Methode qui met une communaute chance spécifique au dessus du paquet (au debut de la liste)</p>
	 * <p>Deux posibilités : </p>
	 * <p> - La carte est deja dans le paquet, on la supprime et on l'ajoute au debut</p>
	 * <p> - La carte n'est pas dans le paquet, on l'ajoute au debut</p>
	 * 
	 * @param CarteCommunaute la carte communaute a mettre au dessus
	 */
	public void MettreCarteCommunauteAuDessus(Carte CarteCommunaute) {
		if(getListeCartesCommunaute() == null) {
			throw new IllegalArgumentException("La liste est null");
		}
		if(CarteCommunaute == null) {
			throw new IllegalArgumentException("La carte est null");
		}
		if(trouverCarteCommunaute(CarteCommunaute.getMessage()) == null) {
			listeCartesCommunaute.add(0, CarteCommunaute);
		}
		else { // la carte est deja dans le paquet
			for(Object Carte : listeCartesCommunaute) {
				if(Carte.toString().contains(CarteCommunaute.getMessage())) {
					listeCartesCommunaute.remove(Carte);
					listeCartesCommunaute.add(0, CarteCommunaute);
					return;
				}
			}
		}
	}
	
	/**
	 * <p>Methode qui met une carte communaute spécifique a la fin du paquet (a la fin de la liste)</p>
	 * <p>Deux posibilités : </p>
	 * <p> - La carte est deja dans le paquet, on la supprime et on l'ajoute a la fin</p>
	 * <p> - La carte n'est pas dans le paquet, on l'ajoute a la fin</p>
	 * 
	 * @param CarteCommunaute la carte communaute a mettre a la fin
	 */
	public void MettreCarteCommunauteAuFond(Carte CarteCommunaute) {
		if(getListeCartesCommunaute() == null) {
			throw new IllegalArgumentException("La liste est null");
		}
		if(CarteCommunaute == null) {
			throw new IllegalArgumentException("La carte est null");
		}
		if(trouverCarteCommunaute(CarteCommunaute.getMessage()) == null) {
			ajouterCarteCommunaute(CarteCommunaute);
		}
		else { // la carte est deja dans le paquet
			for(Object Carte : listeCartesCommunaute) {
				if(Carte.toString().contains(CarteCommunaute.getMessage())) {
					listeCartesCommunaute.remove(Carte);
					ajouterCarteCommunaute(CarteCommunaute);
					return;
				}
			}
		}
	}
	
	/**
	 * <p>Methode qui tire la premiere communaute chance du paquet</p>
	 * <p>Deux possibilités : </p>
	 * <p> - l'effet de la carte est immédiat, on applique l'effet sur le joueur et on remet la carte au fond du paquet</p>
	 * <p> - l'effet de la carte n'est pas immédiat, le joueur garde la carte</p>
	 * 
	 * @param joueur le joueur qui tire une carte communaute
	 * @throws PartieException 
	 */
	public void TirerUneCarteCommunaute(Joueur joueur) throws PartieException {
		if(joueur == null) {
			throw new IllegalArgumentException("Le joueur est null");
		}
		Carte CarteTiree = (Carte) listeCartesCommunaute.get(0);
		if(CarteTiree.isEffetImmediat()) {
			CarteTiree.appliquerEffets(joueur);
			MettreCarteCommunauteAuFond(CarteTiree);
		}
		else {
			joueur.ajouterCarte(CarteTiree);
			getListeCartesCommunaute().remove(0);
		}		
	}
	
	/**
	 * <p>Methode qui cherche une carte communaute dans la liste de cartes communaute en fonction de son message</p>
	 * 
	 * @param message le message de la carte a trouver
	 * @return la carte trouvée ou null sinon
	 */
	public Carte trouverCarteCommunaute(String message) {	
		if(getListeCartesCommunaute() == null) {
			throw new IllegalArgumentException("La liste est null");
		}
		if(message == null || message.trim().isEmpty()){
			throw new IllegalArgumentException("Message vide ou null");
		}
		for(Object Carte : listeCartesCommunaute) {
			if(Carte.toString().contains(message)) {
				return (Carte) Carte;
			}
		}
		return null;
	}
	
	
	// === Fonctions de gestion des coordonnees ===
	/**
	 * <p>Methode qui initialise les coordonnees en appelant le parseur du fichier les contenant</p>
	 */
	public void initialiserCoordonnees() {
		try {
			Fichier.lire(nomDuFichierCoordonnees, premierParserCoordonnees);
		}
		catch (IllegalArgumentException e) {
			System.err.println(e.getMessage());
		}
	}
	
	/**
	 * <p>Methode qui affiche les coordonnée dans l'ordre de la liste des coordonnées</p>
	 */
	public void afficherCoordonnees() {	
		if(getListeCoordonnees() == null) {
			throw new IllegalArgumentException("La liste est null");
		}
		for(Object Coords : listeCoordonnees) {
			System.out.println(Coords.toString());
		}
	}
	
	/**
	 * <p>Methode qui ajoute une coordonnée a la fin de la liste des coordonnées</p>
	 * 
	 * @param Coords la coordonnée a ajouter
	 */
	public void ajouterCoordonnees(Coordonnees Coords) {	
		if(getListeCoordonnees() == null) {
			throw new IllegalArgumentException("La liste est null");
		}
		if(Coords == null) {
			throw new IllegalArgumentException("La case a ajouter est null");
		}
		getListeCoordonnees().add(Coords);
	}
		
	

	// === Getters / Setters ===
	
	public int getNbJoueurs() {
		return nbJoueurs;
	}
	public void setNbJoueurs(int nbJoueurs) {
		if(nbJoueurs < 0) {
			throw new IllegalArgumentException("Le nombre de joueur est négatif");
		}
		this.nbJoueurs = nbJoueurs;
	}
	public int getIndexJoueurActif() {
		return indexJoueurActif;
	}
	public void setIndexJoueurActif(int indexJoueurActif) {
		if(nbJoueurs < 0) {
			throw new IllegalArgumentException("L'index du joueur actif est négatif");
		}
		this.indexJoueurActif = indexJoueurActif;
	}
	public List<Case> getListeCases() {
		return listeCases;
	}
	public void setListeCases(List<Case> listeCases) {
		this.listeCases = listeCases;
	}
	public int getDerniereCase() {
		return DerniereCase;
	}
	public void setDerniereCase(int derniereCase) {
		if(derniereCase < 0) {
			throw new IllegalArgumentException("La derniere case est négative");
		}
		DerniereCase = derniereCase;
	}
	public List<Joueur> getListeJoueurs() {
		return listeJoueurs;
	}
	public void setListeJoueurs(List<Joueur> listeJoueurs) {
		this.listeJoueurs = listeJoueurs;
	}
	public List<Carte> getListeCartesChances() {
		return listeCartesChances;
	}
	public void setListeCartesChances(List<Carte> listeCartesChances) {
		this.listeCartesChances = listeCartesChances;
	}	
	
	public List<Carte> getListeCartesCommunaute() {
		return listeCartesCommunaute;
	}
	public void setListeCartesCommunaute(List<Carte> listeCartesCommunaute) {
		this.listeCartesCommunaute = listeCartesCommunaute;
	}
	public List<Coordonnees> getListeCoordonnees() {
		return listeCoordonnees;
	}
	public void setListeCoordonnees(List<Coordonnees> listeCoordonnees) {
		this.listeCoordonnees = listeCoordonnees;
	}
}
