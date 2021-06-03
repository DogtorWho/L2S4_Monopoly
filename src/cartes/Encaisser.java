package cartes;

import partie.Joueur;

	public class Encaisser extends Carte {
	
	int Montant;

	
	public Encaisser(String message, int montant, boolean isChance) {
		super(message, isChance);
		setMontant(montant);
	}
	
	@Override
	public String toString() {
		return "Encaisser [Montant=" + Montant + ", getMessage()=" + getMessage() + "]";
	}

	@Override
	public void appliquerEffets(Joueur joueur) {
		joueur.ajouterArgent(getMontant());
	}
	
	
	
	public int getMontant() {
		return Montant;
	}
	public void setMontant(int montant) {
		this.Montant = montant;
	}
}
