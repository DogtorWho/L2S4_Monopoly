package cartes.communautes;

import cartes.Carte;
import partie.Joueur;
import partie.exceptions.PartieException;

/**
 * La classe PiocherChance permet de traiter les effets des cartes de type CHANCE
 */
public class PiocherChance extends Carte{
	
	/**
	 * entier qui stock la somme d'argent que le joueur devra payer
	 */
	int Montant;
	
	public PiocherChance(String message, int montant, boolean isChance) {
		super(message, isChance);
		setMontant(montant);
	}
	
	@Override
	public String toString() {
		return "PiocherChance [Montant=" + Montant + ", getMessage()=" + getMessage() + "]";
	}

	@Override
	public void appliquerEffets(Joueur joueur) throws PartieException {
		
	}
	
	public int getMontant() {
		return Montant;
	}
	public void setMontant(int montant) {
		this.Montant= montant;
	}
}