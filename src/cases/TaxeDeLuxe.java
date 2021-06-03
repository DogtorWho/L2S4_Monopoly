package cases;

import partie.Joueur;
import partie.Plateau;
import partie.exceptions.BankruptException;

public class TaxeDeLuxe extends Case {
	
	private int taxe;
	
	public TaxeDeLuxe(int Position, int Taxe) {
		super(Position);
		setTaxe(Taxe);	
	}
	
	@Override
	public String toString() {
		return "TaxeDeLuxe [taxe=" + taxe + ", getPosition()=" + getPosition() + ", getProprietaire()=" + getProprietaire() + "]";
	}

	@Override
	public void appliquerEffets(Joueur joueur) throws BankruptException {
		if(joueur == null) {
			throw new IllegalArgumentException("Le joueur est null");
		}
		joueur.retirerArgent(getTaxe());
		((ParkingGratuit) Plateau.getPlateau().getCase(Plateau.getPlateau().trouverPositionCase("ParkingGratuit"))).AjouterArgentAuMilieu(getTaxe());
	}


	public int getTaxe() {
		return taxe;
	}
	public void setTaxe(int taxe) {
		this.taxe = taxe;
	}
}
