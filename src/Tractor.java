
public class Tractor {

	private int current_sand, X, Y; //X is the row where the robot is, Y is the column. 
	
	public Tractor () {
		this.current_sand = 0;
		this.X = 0;
		this.Y = 0;
	}

	public int getCurrent_sand() {
		return current_sand;
	}

	public void setCurrent_sand(int current_sand) {
		this.current_sand = current_sand;
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
