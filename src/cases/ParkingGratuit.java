package cases;

import partie.Joueur;

/**
 * La classe ParkingGratuit permet de traiter les effets des cases de type PARKING GRATUIT
 */
public class ParkingGratuit extends Case {
	
	/**
	 * entier qui stock la somme que le joueur recevra
	 */
	private int ArgentAuMilieu; // l'argent déposé au milieu du plateau pendant la partie

	public ParkingGratuit(int Position) {
		super(Position);
		setArgentAuMilieu(0);
	}
	
	@Override
	public String toString() {
		return "ParkingGratuit [ArgentAuMilieu=" + ArgentAuMilieu + ", getPosition()=" + getPosition() + ", getProprietaire()=" + getProprietaire() + "]";
	}

	@Override
	public void appliquerEffets(Joueur joueur) {
		if(joueur == null) {
			throw new IllegalArgumentException("Le joueur est null");
		}
		joueur.ajouterArgent(getArgentAuMilieu());
		setArgentAuMilieu(0);
	}
	
	public void AjouterArgentAuMilieu(int argentAjoute) {
		this.ArgentAuMilieu = this.ArgentAuMilieu + argentAjoute;
	}

	
	public int getArgentAuMilieu() {
		return ArgentAuMilieu;
	}
	public void setArgentAuMilieu(int argentAuMilieu) {
		ArgentAuMilieu = argentAuMilieu;
	}
}
