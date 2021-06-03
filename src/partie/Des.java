package partie;

import java.util.Random;

public class Des {
	
	private static Des instance = null;
	private int Somme;
	private int Des1;
	private int Des2;
	private boolean Double;
	private Random _alea; 
	
	
	private Des() {
		Somme = 0;
		Des1 = 0;
		Des2 = 0;
		setDouble(false);
		set_alea(new Random());
	}
	
	public static Des getDes() {
		if(instance == null) {
			instance = new Des();
		}
		return instance;
	}
	

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
