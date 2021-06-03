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

public class Plateau {
	
	private static Plateau instance = null;
	
	private int nbJoueurs = 0;
	private int indexJoueurActif = 0;
	
	private static String nomDuFichierPlateau = "Data/Terrains.csv";
	private static String nomDuFichierCartesChances = "Data/CartesChance.csv";
	private static String nomDuFichierCartesCommunaute = "Data/CartesCommunaute.csv";
	private static String nomDuFichierCoordonnees = "Data/CoordonneesCases.csv";
	
	private Parser premierParserPlateau = null;
	private Parser premierParserCartesChances = null;
	private Parser premierParserCartesCommunaute = null;
	private Parser premierParserCoordonnees = null;
	
	private List<Joueur> listeJoueurs = new ArrayList<Joueur>();
	private List<Case> listeCases = new ArrayList<Case>();
	private List<Carte> listeCartesChances = new ArrayList<Carte>();
	private List<Carte> listeCartesCommunaute = new ArrayList<Carte>();
	private List<Coordonnees> listeCoordonnees = new ArrayList<Coordonnees>();
	
	private int DerniereCase;
	
		
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
	public static Plateau getPlateau() {
		if(instance == null) {
			instance = new Plateau();
		}
		return instance;
	}
			
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
	
	public void ajouterCase(Case Case) {	
		if(getListeCases() == null) {
			throw new IllegalArgumentException("La liste est null");
		}
		if(Case == null) {
			throw new IllegalArgumentException("La case a ajouter est null");
		}
		getListeCases().add(Case);
	}
	
	public Case getCase(int numeroCase) {
		if(getListeCases() == null) {
			throw new IllegalArgumentException("La liste est null");
		}
		if(numeroCase < 0) {
			throw new IllegalArgumentException("Le numéro de case est négatif ou n'a pas ete trouvé");
		}
		return (Case) (getListeCases().get(numeroCase));
	}
	
	public void afficherUneCase(int numeroCase) {
		if(getListeCases() == null) {
			throw new IllegalArgumentException("La liste est null");
		}
		if(numeroCase < 0) {
			throw new IllegalArgumentException("Le numéro de case est négatif");
		}
		System.out.println(getCase(numeroCase).toString());
	}
	
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
	
	public void ajouterJoueur(Joueur joueur) {
		if(joueur == null) {
			throw new IllegalArgumentException("Le joueur a ajouter est null");
		}
		setNbJoueurs(getNbJoueurs() + 1);	
		getListeJoueurs().add(joueur);
	}
	
	
	// === Fonctions de gestion des cartes chance ===
	
	public void initialiserCartesChances() {
		try {
			Fichier.lire(nomDuFichierCartesChances, premierParserCartesChances);
		}
		catch (IllegalArgumentException e) {
			System.err.println(e.getMessage());
		}
	}
	
	public void afficherCartesChances() {	
		if(getListeCartesChances() == null) {
			throw new IllegalArgumentException("La liste est null");
		}
		for(Object Carte : listeCartesChances) {
			System.out.println(Carte.toString());
		}
	}
	
	public void ajouterCarteChance(Carte CarteChance) {	
		if(getListeCartesChances() == null) {
			throw new IllegalArgumentException("La liste est null");
		}
		if(CarteChance == null) {
			throw new IllegalArgumentException("La carte chance a ajouter est null");
		}
		listeCartesChances.add(CarteChance);
	}
	
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
	
	public void initialiserCartesCommunaute() {
		try {
			Fichier.lire(nomDuFichierCartesCommunaute, premierParserCartesCommunaute);
		}
		catch (IllegalArgumentException e) {
			System.err.println(e.getMessage());
		}
	}
	
	public void afficherCartesCommunaute() {	
		if(getListeCartesCommunaute() == null) {
			throw new IllegalArgumentException("La liste est null");
		}
		for(Object Carte : listeCartesCommunaute) {
			System.out.println(Carte.toString());
		}
	}
	
	public void ajouterCarteCommunaute(Carte CarteCommunaute) {	
		if(getListeCartesCommunaute() == null) {
			throw new IllegalArgumentException("La liste est null");
		}
		if(CarteCommunaute == null) {
			throw new IllegalArgumentException("La carte communaute a ajouter est null");
		}
		listeCartesCommunaute.add(CarteCommunaute);
	}
	
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
	
		public void initialiserCoordonnees() {
			try {
				Fichier.lire(nomDuFichierCoordonnees, premierParserCoordonnees);
			}
			catch (IllegalArgumentException e) {
				System.err.println(e.getMessage());
			}
		}
		
		public void afficherCoordonnees() {	
			if(getListeCoordonnees() == null) {
				throw new IllegalArgumentException("La liste est null");
			}
			for(Object Coords : listeCoordonnees) {
				System.out.println(Coords.toString());
			}
		}
		
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
