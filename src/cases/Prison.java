package cases;

import partie.Joueur;
import partie.exceptions.PartieException;

/**
 * La classe Prison permet de traiter les effets des cases de type PRISON
 */
public class Prison extends Case {
	
	public Prison(int Position) {
		super(Position);
	}
	
	@Override
	public String toString() {
		return "^Prison [getPosition()=" + getPosition() + ", getProprietaire()=" + getProprietaire() + "]";
	}

	@Override
	public void appliquerEffets(Joueur joueur) throws PartieException {	
		if(joueur == null) {
			throw new IllegalArgumentException("Le joueur est null");
		}
		if( ! joueur.isPrisonnier()) {
			joueur.setPrisonnier(true);
			joueur.setToursEnPrison(1);
		}
		else {
			joueur.SortirDePrisonApres3Tours();
		}		
	}
}
