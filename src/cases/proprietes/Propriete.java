package cases.proprietes;

import cases.Case;

/**
 * La classe Propriete stock toutes les données relatives a une propriete, qui est une extention de Case
 */
public abstract class Propriete extends Case {
	
	/**
	 * String qui stock le nom de la propriete
	 */
	private String Nom;
	/**
	 * entier qui stock le prix d'une propriete
	 */
	private int PrixAchat;
	/**
	 * boolean qui est vrai quand la propriete est en hypotheque
	 */
	private boolean EnHypotheque;
	
	Propriete(int Position, String Nom, int PrixAchat) {
		super(Position);
		setNom(Nom);
		setPrixAchat(PrixAchat);
		setEnHypotheque(false);
	}

	public abstract int calculDuLoyer();

	
	
	public String getNom() {
		return Nom;
	}
	public void setNom(String nom) {
		Nom = nom;
	}
	public int getPrixAchat() {
		return PrixAchat;
	}
	public void setPrixAchat(int prixAchat) {
		PrixAchat = prixAchat;
	}
	public boolean isEnHypotheque() {
		return EnHypotheque;
	}
	public void setEnHypotheque(boolean enHypotheque) {
		EnHypotheque = enHypotheque;
	}
}
