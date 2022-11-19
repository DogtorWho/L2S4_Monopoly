package cartes.communautes;

import cartes.Carte;
import partie.Joueur;
import partie.Plateau;
import partie.exceptions.BankruptException;

/**
 * La classe Anniversaire permet de traiter les effets des cartes de type ANNIVERSAIRE
 */
public class Anniversaire extends Carte{

	/**
	 * entier qui stock la somme d'argent que les joueurs devront payer au joueur qui tire la carte
	 */
	int Montant;
	
	public Anniversaire(String message, int Montant, boolean isChance) {
		super(message, isChance);
		setMontant(Montant);
	}
	
	@Override
	public String toString() {
		return "Anniversaire [Montant=" + Montant + ", getMessage()=" + getMessage() + "]";
	}

	@Override
	public void appliquerEffets(Joueur joueur) throws BankruptException {
		joueur.ajouterArgent(Montant*(Plateau.getPlateau().getNbJoueurs()-1));
		if(Plateau.getPlateau().getListeJoueurs() == null) {
			throw new IllegalArgumentException("La liste est null");
		}
		for(Joueur joueurs : Plateau.getPlateau().getListeJoueurs()) {
			if(joueurs!=joueur) {
				joueurs.retirerArgent(Montant);
			}
		}
	}
	
	public int getMontant() {
		return Montant;
	}
	public void setMontant(int montant) {
		this.Montant= montant;
	}
}