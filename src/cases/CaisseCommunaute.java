package cases;

import partie.Joueur;
import partie.Plateau;
import partie.exceptions.PartieException;

/**
 * La classe CaisseCommunaute permet de traiter les effets des cases de type CAISSE COMMUNAUTE
 */
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
