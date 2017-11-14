package Domain;

public class Movement {
	private int X, Y;
	
	public Movement(int x, int y) { 
		this.X = x;
		this.Y = y;
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
	
	
	public void printMove() {
		System.out.println("[" + X + "," + Y + "]");
	}
}
