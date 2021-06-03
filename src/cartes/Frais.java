package cartes;

import partie.Joueur;
import partie.exceptions.BankruptException;

public class Frais extends Carte {
	
	int MontantM;
	int MontantH;

	
	public Frais(String message, int montantM, int montantH, boolean isChance) {
		super(message, isChance);
		setMontantM(montantM);
		setMontantH(montantH);
	}
	
	@Override
	public String toString() {
		return "Frais [MontantM=" + MontantM + ", MontantH=" + MontantH + ", getMessage()=" + getMessage() + "]";
	}

	@Override
	public void appliquerEffets(Joueur joueur) throws BankruptException {
		joueur.retirerArgent((MontantM*joueur.NombreDeMaison())+(MontantH*joueur.NombreHotel()));
	}
	
	
	
	public int getMontantM() {
		return MontantM;
	}
	public void setMontantM(int montant) {
		this.MontantM= montant;
	}
	public int getMontantH() {
		return MontantH;
	}
	public void setMontantH(int montant) {
		this.MontantH = montant;
	}
}
