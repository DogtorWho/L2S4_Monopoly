package cartes;

import partie.Joueur;
import partie.exceptions.BankruptException;

public class Payer extends Carte {
	
	int Montant;

	
	public Payer(String message, int montant, boolean isChance) {
		super(message, isChance);
		setMontant(montant);
	}
	
	@Override
	public String toString() {
		return "Payer [Montant=" + Montant + ", getMessage()=" + getMessage() + "]";
	}

	@Override
	public void appliquerEffets(Joueur joueur) throws BankruptException {
		joueur.retirerArgent(getMontant());
	}
	
	
	
	public int getMontant() {
		return Montant;
	}
	public void setMontant(int montant) {
		this.Montant = montant;
	}
}
