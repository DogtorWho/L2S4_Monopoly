package cases.proprietes;

import partie.Joueur;
import partie.exceptions.PartieException;

/**
 * La classe TerrainConstructible permet de traiter les effets des proprietes de type TERRAIN CONSTRUCTIBLE
 */
public class TerrainConstructible extends Propriete {

	/**
	 * String qui stock la couleur de la propriete
	 */
	private String Couleur;
	/**
	 * entier qui stock le nombre de maisons sur le terrain
	 */
	private int NombreMaison;
	/**
	 * entier qui stock le prix d'une maison sur le terrain
	 */
	private int PrixMaison;
	/**
	 * entier qui stock le prix du loyer si le terrain n'a pas de maisons
	 */
	private int TerrainNu;
	/**
	 * entier qui stock le prix du loyer si le terrain a 1 maison
	 */
	private int UneMaison;
	/**
	 * entier qui stock le prix du loyer si le terrain a 2 maisons
	 */
	private int DeuxMaisons;
	/**
	 * entier qui stock le prix du loyer si le terrain a 3 maisons
	 */
	private int TroisMaisons;
	/**
	 * entier qui stock le prix du loyer si le terrain a 4 maisons
	 */
	private int QuatreMaisons;
	/**
	 * entier qui stock le prix du loyer si le terrain a 1 hotel (5 maisons)
	 */
	private int UnHotel;
	
	public TerrainConstructible(int Position, String Nom, int PrixAchat, String Couleur, int PrixMaison,
			int TerrainNu, int UneMaison, int DeuxMaisons, int TroisMaisons, int QuatreMaisons, int UnHotel) {
		
		super(Position, Nom, PrixAchat);
		setCouleur(Couleur);
		setNombreMaison(0);
		setPrixMaison(PrixMaison);
		setTerrainNu(TerrainNu);
		setUneMaison(UneMaison);
		setDeuxMaisons(DeuxMaisons);
		setTroisMaisons(TroisMaisons);
		setQuatreMaisons(QuatreMaisons);
		setUnHotel(UnHotel);
	}	

	@Override
	public String toString() {
		return "TerrainConstructible [Couleur=" + Couleur + ", NombreMaison=" + NombreMaison + ", PrixMaison="
				+ PrixMaison + ", TerrainNu=" + TerrainNu + ", UneMaison=" + UneMaison + ", DeuxMaisons=" + DeuxMaisons
				+ ", TroisMaisons=" + TroisMaisons + ", QuatreMaisons=" + QuatreMaisons + ", UnHotel=" + UnHotel
				+ ", getNom()=" + getNom() + ", getPrixAchat()=" + getPrixAchat() + ", isEnHypotheque()="
				+ isEnHypotheque() + ", getPosition()=" + getPosition() + ", getProprietaire()=" + getProprietaire() + "]";
	}

	@Override
	public int calculDuLoyer() {
		switch(getNombreMaison()) {
			case 0 : return getTerrainNu();
			case 1 : return getUneMaison();
			case 2 : return getDeuxMaisons();
			case 3 : return getTroisMaisons();
			case 4 : return getQuatreMaisons();
			case 5 : return getUnHotel();
			default : return -1;
		}
	}

	@Override
	public void appliquerEffets(Joueur joueur) throws PartieException {
		if(joueur == null) {
			throw new IllegalArgumentException("Le joueur est null");
		}
		if(getProprietaire() != null && getProprietaire() != joueur) {
			if(getProprietaire().PossedeToutesLesMemesCouleurs(getCouleur()) && getNombreMaison() == 0) {
				joueur.retirerArgent(calculDuLoyer() * 2);
				getProprietaire().ajouterArgent(calculDuLoyer() * 2);
			}
			else {
				joueur.retirerArgent(calculDuLoyer());
				getProprietaire().ajouterArgent(calculDuLoyer());
			}
		}
	}
	
	
	public String getCouleur() {
		return Couleur;
	}
	public void setCouleur(String couleur) {
		Couleur = couleur;
	}
	public int getNombreMaison() {
		return NombreMaison;
	}
	public void setNombreMaison(int nombreMaison) {
		NombreMaison = nombreMaison;
	}
	public int getTerrainNu() {
		return TerrainNu;
	}
	public void setTerrainNu(int terrainNu) {
		TerrainNu = terrainNu;
	}
	public int getUneMaison() {
		return UneMaison;
	}
	public void setUneMaison(int uneMaison) {
		UneMaison = uneMaison;
	}
	public int getDeuxMaisons() {
		return DeuxMaisons;
	}
	public void setDeuxMaisons(int deuxMaisons) {
		DeuxMaisons = deuxMaisons;
	}
	public int getTroisMaisons() {
		return TroisMaisons;
	}
	public void setTroisMaisons(int troisMaisons) {
		TroisMaisons = troisMaisons;
	}
	public int getQuatreMaisons() {
		return QuatreMaisons;
	}
	public void setQuatreMaisons(int quatreMaisons) {
		QuatreMaisons = quatreMaisons;
	}
	public int getUnHotel() {
		return UnHotel;
	}
	public void setUnHotel(int unHotel) {
		UnHotel = unHotel;
	}
	public int getPrixMaison() {
		return PrixMaison;
	}
	public void setPrixMaison(int prixMaison) {
		PrixMaison = prixMaison;
	}
}
