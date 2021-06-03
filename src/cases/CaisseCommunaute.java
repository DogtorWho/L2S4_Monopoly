package cases;

import partie.Joueur;
import partie.Plateau;
import partie.exceptions.PartieException;

public class CaisseCommunaute extends Case {

	public CaisseCommunaute(int Position) {
		super(Position);
	}
	
	@Override
	public String toString() {
		return "CaisseCommunaute [getPosition()=" + getPosition() + ", getProprietaire()=" + getProprietaire() + "]";
	}

	@Override
	public void appliquerEffets(Joueur joueur) throws PartieException {
		Plateau.getPlateau().TirerUneCarteCommunaute(joueur);	
	}	
}
