package partie;

public class Coordonnees {
	
	private int Position;
	private int X;
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
