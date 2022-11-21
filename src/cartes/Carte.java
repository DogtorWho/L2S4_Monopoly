package cartes;

import partie.Joueur;
import partie.exceptions.PartieException;

/**
 * La classe Carte stock toutes les données relatives à une carte, qui sera ensuite ajouté à la liste des cartes du plateau
 */
public abstract class Carte {
	
	/**
	 * String qui stock le message de la carte
	 */
	private String message;
	/**
	 * boolean qui est vrai quand l'effet de la carte doit etre appliqué des qu'on la pioche
	 * Ici il n'y a que SortirDePrison qui n'est pas immediat
	 */
	private boolean effetImmediat = true; // tous sauf sortir de prison
	/**
	 * boolean qui est vrai quand la carte est dans le paquet de carte chances
	 * Sert quand on doit replacer dans un des paquets du plateau la carte qu'un joueur utilise
	 */
	private boolean PaquetChance;
	
	protected Carte(String message, boolean isChance) {
		setMessage(message);
		setPaquetChance(isChance);
	}
	
	/**
	 * <p>Methode abstraite qui applique les effets des differentes cartes</p>
	 * 
	 * @param joueur le joueur qui va recevoir l'effet
	 * @throws PartieException
	 */
	public abstract void appliquerEffets(Joueur joueur) throws PartieException;

	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public boolean isEffetImmediat() {
		return effetImmediat;
	}
	public void setEffetImmediat(boolean effetImmediat) {
		this.effetImmediat = effetImmediat;
	}
	public boolean isPaquetChance() {
		return PaquetChance;
	}
	public void setPaquetChance(boolean paquetChance) {
		PaquetChance = paquetChance;
	}
}
