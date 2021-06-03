package cases;

import partie.Joueur;
import partie.exceptions.PartieException;

public abstract class Case {
	
	private int Position;
	private Joueur Proprietaire;
	
	protected Case(int position){
		setPosition(position);
		setProprietaire(null);
	}
	
	public abstract void appliquerEffets(Joueur joueur) throws PartieException;
	
	
	
	public int getPosition() {
		return Position;
	}
	public void setPosition(int position) {
		Position = position;
	}
	public Joueur getProprietaire() {
		return Proprietaire;
	}
	public void setProprietaire(Joueur proprietaire) {
		Proprietaire = proprietaire;
	}
}
