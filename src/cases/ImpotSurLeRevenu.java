package cases;

import partie.Joueur;
import partie.Plateau;
import partie.exceptions.BankruptException;

/**
 * La classe ImpotSurLeRevenu permet de traiter les effets des cases de type IMPOT SUR LE REVENU
 */
public class ImpotSurLeRevenu extends Case {

	/**
	 * entier qui stock la somme que le joueur devra payer
	 */
	private int impot;
	
	public ImpotSurLeRevenu(int Position, int Impot) {
		super(Position);
		setImpot(Impot);	
	}
	
	@Override
	public String toString() {
		return "ImpotSurLeRevenu [impot=" + impot + ", getPosition()=" + getPosition() + ", getProprietaire()=" + getProprietaire() + "]";
	}

	@Override
	public void appliquerEffets(Joueur joueur) throws BankruptException {
		if(joueur == null) {
			throw new IllegalArgumentException("Le joueur est null");
		}
		joueur.retirerArgent(getImpot());
		((ParkingGratuit) Plateau.getPlateau().getCase(Plateau.getPlateau().trouverPositionCase("ParkingGratuit"))).AjouterArgentAuMilieu(getImpot());
	}


	public int getImpot() {
		return impot;
	}
	public void setImpot(int impot) {
		this.impot = impot;
	}
}
