package cartes;

import partie.Joueur;
import partie.Plateau;
import partie.exceptions.PartieException;

public class Deplacement extends Carte {
	
	private String NomCase;
	private int reculer;
	
	public Deplacement(String message, String NomCase, int reculer, boolean isChance) {
		super(message, isChance);
		setNomCase(NomCase);
		setReculer(reculer);
	}
	
	@Override
	public String toString() {
		return "Deplacement [NomCase=" + NomCase + ", getMessage()=" + getMessage() + "]";
	}

	@Override
	public void appliquerEffets(Joueur joueur) throws PartieException {
		if( ! NomCase.isEmpty()) {
			joueur.teleporter(Plateau.getPlateau().trouverPositionCase(NomCase), true);
		}
		else {
			joueur.reculer(reculer);
		}	
	}
	
	
	
	public String getNomCase() {
		return NomCase;
	}
	public void setNomCase(String nomCase) {
		NomCase = nomCase;
	}
	public int getReculer() {
		return reculer;
	}
	public void setReculer(int reculer) {
		this.reculer = reculer;
	}
}