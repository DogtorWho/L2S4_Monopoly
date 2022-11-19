package cases.proprietes;

import partie.Des;
import partie.Joueur;
import partie.exceptions.PartieException;

/**
 * La classe Compagnie permet de traiter les effets des proprietes de type COMPAGNIE
 */
public class Compagnie extends Propriete {

	public Compagnie(int Position, String Nom, int PrixAchat) {
		super(Position, Nom, PrixAchat);
	}
	
	@Override
	public String toString() {
		return "Compagnie [getNom()=" + getNom() + ", getPrixAchat()=" + getPrixAchat() + ", isEnHypotheque()="
				+ isEnHypotheque() + ", getPosition()=" + getPosition() + ", getProprietaire()=" + getProprietaire() + "]";
	}

	@Override
	public int calculDuLoyer() {
		return Des.getDes().getSomme();
	}

	@Override
	public void appliquerEffets(Joueur joueur) throws PartieException {
		if(joueur == null) {
			throw new IllegalArgumentException("Le joueur est null");
		}
		if(getProprietaire() != null && getProprietaire() != joueur) {
			if(getProprietaire().PossedeToutesLesCompagnies()) {
				joueur.retirerArgent(calculDuLoyer() * 10);
				getProprietaire().ajouterArgent(calculDuLoyer() * 10);
			}
			else {
				joueur.retirerArgent(calculDuLoyer() * 4);
				getProprietaire().ajouterArgent(calculDuLoyer() * 4);
			}
		}
	}
}
