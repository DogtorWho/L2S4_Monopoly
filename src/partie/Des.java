package partie;

import java.util.Random;

/**
 * La classe Des gère le lancé des 2 dés du Monopoly
 * On utilise un type Singleton pour avoir access aux dès dans toutes les autres classes
 */
public class Des {
	
	/**
	 * instance du Singleton Des
	 */
	private static Des instance = null;	
	/**
	 * entier qui stock la somme des 2 dés lancés
	 */
	private int Somme;	
	/**
	 * entier qui stock la valeur du dés 1 lancé
	 */
	private int Des1;
	/**
	 * entier qui stock la valeur du dés 2 lancé
	 */
	private int Des2;	
	/**
	 * boolean qui est vrai quand la valeur des 2 dés sont identiques
	 */
	private boolean Double;
	/**
	 * nombre aléatoire qui varie entre 1 et 6
	 */
	private Random _alea; 
	
	
	private Des() {
		Somme = 0;
		Des1 = 0;
		Des2 = 0;
		setDouble(false);
		set_alea(new Random());
	}
	
	/**
	 * <p>Methode qui sert a recuperer l'instance du Singleton Des dans les autres classes</p>
	 * 
	 * @return l'instance de Des
	 */
	public static Des getDes() {
		if(instance == null) {
			instance = new Des();
		}
		return instance;
	}
	
	/**
	 * <p>Methode qui "Lance" les dés en generant un nombre aléatoire entre 1 et 6 pour les 2 dés, set la Somme et le boolean Double en fonction des resultats</p>
	 */
	public void LancerDes(){
		setDes1(this._alea.nextInt(5)+1);
		setDes2(this._alea.nextInt(5)+1);
		setSomme(getDes1()+getDes2());
		if(getDes1() == getDes2()) {
			setDouble(true);
		}
		else {
			setDouble(false);
		}
	}
	
	
	@Override
	public String toString() {
		return "Des [Somme=" + Somme + ", Des1=" + Des1 + ", Des2=" + Des2 + ", EstUnDouble=" + Double + "]";
	}


	
	public int getSomme() {
		return Somme;
	}
	public void setSomme(int somme) {
		if(somme < 2 || somme > 12) {
			throw new IllegalArgumentException("La somme n'est pas possible");
		}
		Somme = somme;
	}
	public int getDes1() {
		return Des1;
	}
	public void setDes1(int des1) {
		if(des1 < 1 || des1 > 6) {
			throw new IllegalArgumentException("Le des1 n'est pas possible");
		}
		Des1 = des1;
	}
	public int getDes2() {
		return Des2;
	}
	public void setDes2(int des2) {
		if(des2 < 1 || des2 > 6) {
			throw new IllegalArgumentException("Le des2 n'est pas possible");
		}
		Des2 = des2;
	}
	public boolean isDouble() {
		return Double;
	}
	public void setDouble(boolean Double) {
		this.Double = Double;
	}
	public Random get_alea() {
		return _alea;
	}
	public void set_alea(Random _alea) {
		this._alea = _alea;
	}
}
