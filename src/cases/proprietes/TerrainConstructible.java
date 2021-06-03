package cases.proprietes;

import partie.Joueur;
import partie.exceptions.PartieException;

public class TerrainConstructible extends Propriete {

	private String Couleur;
	private int NombreMaison;
	private int PrixMaison;
	private int TerrainNu;
	private int UneMaison;
	private int DeuxMaisons;
	private int TroisMaisons;
	private int QuatreMaisons;
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
