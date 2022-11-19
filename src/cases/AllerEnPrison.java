package cases;

import partie.Joueur;
import partie.Plateau;
import partie.exceptions.PartieException;

/**
 * La classe AllerEnPrison permet de traiter les effets des cases de type ALLEZ EN PRISON
 */
public class AllerEnPrison extends Case {

	public AllerEnPrison(int Position) {
		super(Position);
	}
	
	@Override
	public String toString() {
		return "AllerEnPrison [getPosition()=" + getPosition() + ", getProprietaire()=" + getProprietaire() + "]";
	}
	
	@Override
	public void appliquerEffets(Joueur joueur) throws PartieException {
		joueur.teleporter(Plateau.getPlateau().trouverPositionCase("^Prison"), false);
	}
}
