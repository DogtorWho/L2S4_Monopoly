package cases;

import partie.Joueur;

/**
 * La classe SimpleVisite permet de traiter les effets des cases de type SIMPLE VISITE
 */
public class SimpleVisite extends Case {
	
	public SimpleVisite(int Position) {
		super(Position);
	}
	
	@Override
	public String toString() {
		return "SimpleVisite [getPosition()=" + getPosition() + ", getProprietaire()=" + getProprietaire() + "]";
	}

	@Override
	public void appliquerEffets(Joueur joueur) {
		// ne rien faire
	}
}
