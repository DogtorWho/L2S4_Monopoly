package partie;

/**
 * La classe Coordonnees permet de stocker les coordonnées X et Y d'une case du Plateau, en fonction de sa position
 * Cette classe est utile dans l'UI pour afficher les pions des joueurs
 * 
 * @author doctorwho
 *
 */
public class Coordonnees {
	
	/**
	 * entier qui stock la position de la case associée aux coordonnées
	 */
	private int Position;
	/**
	 * entier qui stock la valeur de l'axe X de la coordonnée
	 */
	private int X;
	/**
	 * entier qui stock la valeur de l'axe Y de la coordonnée
	 */
	private int Y;
	
	public Coordonnees(int position, int x, int y){
		setPosition(position);
		setX(x);
		setY(y);	
	}
	
	@Override
	public String toString() {
		return "Coordonnees [Position=" + Position + ", X=" + X + ", Y=" + Y + "]";
	}

	
	public int getPosition() {
		return Position;
	}
	public void setPosition(int position) {
		Position = position;
	}
	public int getX() {
		return X;
	}
	public void setX(int x) {
		X = x;
	}
	public int getY() {
		return Y;
	}
	public void setY(int y) {
		Y = y;
	}
}
