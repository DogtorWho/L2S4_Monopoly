package cases.proprietes;

import partie.Joueur;
import partie.exceptions.PartieException;

/**
 * La classe Gare permet de traiter les effets des proprietes de type GARE
 */
public class Gare extends Propriete {

	public Gare(int Position, String Nom, int PrixAchat) {
		super(Position, Nom, PrixAchat);
	}
	
	@Override
	public String toString() {
		return "Gare [getNom()=" + getNom() + ", getPrixAchat()=" + getPrixAchat() + ", isEnHypotheque()="
				+ isEnHypotheque() + ", getPosition()=" + getPosition() + ", getProprietaire()=" + getProprietaire() + "]";
	}

	@Override
	public int calculDuLoyer() {
		return 50;
	}

	@Override
	public void appliquerEffets(Joueur joueur) throws PartieException {
		if(joueur == null) {
			throw new IllegalArgumentException("Le joueur est null");
		}
		if(getProprietaire() != null && getProprietaire() != joueur) {
			int nbGares = getProprietaire().nombreDeGaresPossedees();
			joueur.retirerArgent(calculDuLoyer() * nbGares);
			getProprietaire().ajouterArgent(calculDuLoyer() * nbGares);
		}
	}
}
