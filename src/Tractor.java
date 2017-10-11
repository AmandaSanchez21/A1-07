import java.util.ArrayList;
import java.util.List;

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
	
	public static List<Movement> moveTractor (Tractor t, Field f) {
		List <Movement> pos_moves = new ArrayList <Movement>();
		
		if (0 <= t.getX() && t.getX() < f.getN_rows()) {
			if(t.getX() + 1 < f.getN_rows()) {
				Movement s = new Movement (t.getX() +1, t.getY());
				pos_moves.add(s);
			}
			
			if(t.getX() - 1 >= 0) {
				Movement n = new Movement(t.getX() -1, t.getY());
				pos_moves.add(n);
			}
		}
		
		if (0 <= t.getY() && t.getY() < f.getN_cols()) {
			if(t.getY() + 1 < f.getN_cols()) {
				Movement e = new Movement(t.getX(), t.getY()+1);
				pos_moves.add(e);
			}
			
			if(t.getY() - 1 >= 0) {
				Movement w = new Movement (t.getX(), t.getY()-1);
				pos_moves.add(w);
			}
		}
		
		return pos_moves;
	}	
}
