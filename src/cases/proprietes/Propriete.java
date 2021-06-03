package cases.proprietes;

import cases.Case;

public abstract class Propriete extends Case {
	
	private String Nom;
	private int PrixAchat;
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
