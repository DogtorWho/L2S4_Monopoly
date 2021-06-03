package cases;

import partie.Joueur;
import partie.Plateau;
import partie.exceptions.PartieException;

public class Chance extends Case {

	public Chance(int Position) {
		super(Position);
	}
	
	@Override
	public String toString() {
		return "Chance [getPosition()=" + getPosition() + ", getProprietaire()=" + getProprietaire() + "]";
	}

	@Override
	public void appliquerEffets(Joueur joueur) throws PartieException {
		Plateau.getPlateau().TirerUneCarteChance(joueur);
	}
}
