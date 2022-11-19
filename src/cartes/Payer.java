package cartes;

import partie.Joueur;
import partie.exceptions.BankruptException;

/**
 * La classe Payer permet de traiter les effets des cartes de type PAYER
 */
public class Payer extends Carte {
	
	/**
	 * entier qui stock la somme d'argent que le joueur devra payer
	 */
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
