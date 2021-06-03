package cartes.communautes;

import cartes.Carte;
import partie.Joueur;
import partie.exceptions.PartieException;

public class PiocherChance extends Carte{

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