package cartes;

import partie.Joueur;
import partie.exceptions.PartieException;

public abstract class Carte {
	
	private String message;
	private boolean effetImmediat = true; // tous sauf sortir de prison
	private boolean PaquetChance;
	
	protected Carte(String message, boolean isChance) {
		setMessage(message);
		setPaquetChance(isChance);
	}
	
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
