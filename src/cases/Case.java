package cases;

import partie.Joueur;
import partie.exceptions.PartieException;

/**
 * La classe Case stock toutes les données relatives a une case, qui sera ensuite ajouter a la liste des cases du plateau
 */
public abstract class Case {
	
	/**
	 * entier qui stock la position de la case sur le plateau
	 */
	private int Position;
	/**
	 * Joueur qui stock le proprietaire de la case si elle est achetée par un joueur (null sinon)
	 */
	private Joueur Proprietaire;
	
	protected Case(int position){
		setPosition(position);
		setProprietaire(null);
	}
	
	/**
	 * <p>Methode abstraite qui applique les effets des differentes cases</p>
	 * 
	 * @param joueur le joueur qui va recevoir l'effet
	 * @throws PartieException
	 */
	public abstract void appliquerEffets(Joueur joueur) throws PartieException;
	
	
	
	public int getPosition() {
		return Position;
	}
	public void setPosition(int position) {
		Position = position;
	}
	public Joueur getProprietaire() {
		return Proprietaire;
	}
	public void setProprietaire(Joueur proprietaire) {
		Proprietaire = proprietaire;
	}
}
