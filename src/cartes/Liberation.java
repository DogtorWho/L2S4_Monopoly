package cartes;

import partie.Joueur;
import partie.Plateau;

/**
 * La classe Liberation permet de traiter les effets des cartes de type LIBERATION
 */
public class Liberation extends Carte {
	
	public Liberation(String message, boolean isChance) {
		super(message, isChance);
		setEffetImmediat(false);
	}
	
	@Override
	public String toString() {
		return "Liberation [getMessage()=" + getMessage() + "]";
	}

	@Override
	public void appliquerEffets(Joueur joueur) {
		if(joueur == null) {
			throw new IllegalArgumentException("Le joueur est null");
		}
		joueur.setPrisonnier(false);
		joueur.setCarteSortirPrison(false);
		joueur.retirerCarte(this);
		Plateau.getPlateau().MettreCarteCommunauteAuFond(this);
	}
}
