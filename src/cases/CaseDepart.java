package cases;

import partie.Joueur;

/**
 * La classe CaseDepart permet de traiter les effets des cases de type CASE DEPART
 */
public class CaseDepart extends Case {
	
	/**
	 * entier qui stock l'argent que recoit un joueur apres avoir fait un tour
	 */
	private int gainTour; // le joueur recoit de l'argent chaque tour en passant par cette case
	
	public CaseDepart(int Position, int gain) {
		super(Position);
		setGainTour(gain);	
	}
	
	@Override
	public String toString() {
		return "CaseDepart [gainTour=" + gainTour + ", getPosition()=" + getPosition() + ", getProprietaire()=" + getProprietaire() + "]";
	}

	@Override
	public void appliquerEffets(Joueur joueur) {
		if(joueur == null) {
			throw new IllegalArgumentException("Le joueur est null");
		}
		joueur.ajouterArgent(gainTour);
	}

	
	public int getGainTour() {
		return gainTour;
	}
	public void setGainTour(int gainTour) {
		this.gainTour = gainTour;
	}
}
