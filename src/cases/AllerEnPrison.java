package cases;

import partie.Joueur;
import partie.Plateau;
import partie.exceptions.PartieException;

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
