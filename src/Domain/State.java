package Domain;
import java.util.*;

public class State {
	private int n_rows, n_cols, K, max, current_sand, X, Y; 
	private int [][] field;
	private List<Node> frontier;
	
	
	
	public State() {
		this.n_rows = 0;
		this.n_cols = 0;
		K = 0;
		this.max = 0;
		this.current_sand = 0;
		X = 0;
		Y = 0;
	}
	
	
	
	public int getN_rows() {
		return n_rows;
	}
	public void setN_rows(int n_rows) {
		this.n_rows = n_rows;
	}
	public int getN_cols() {
		return n_cols;
	}
	public void setN_cols(int n_cols) {
		this.n_cols = n_cols;
	}
	public int getK() {
		return K;
	}
	public void setK(int k) {
		K = k;
	}
	public int getMax() {
		return max;
	}
	public void setMax(int max) {
		this.max = max;
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
	public int[][] getField() {
		return field;
	}
	public void setField(int[][] field) {
		this.field = field;
	}
	
	public List<Node> getFrontier() {
		return frontier;
	}

	public void setFrontier(List<Node> frontier) {
		this.frontier = frontier;
	}
	
	
	
	public static List<Movement> moveTractor (State t) {
		List <Movement> pos_moves = new ArrayList <Movement>();
		
		if (0 <= t.getX() && t.getX() < t.getN_rows()) {
			if(t.getX() + 1 < t.getN_rows()) {
				Movement s = new Movement (t.getX() +1, t.getY());
				pos_moves.add(s);
			}
			
			if(t.getX() - 1 >= 0) {
				Movement n = new Movement(t.getX() -1, t.getY());
				pos_moves.add(n);
			}
		}
		
		if (0 <= t.getY() && t.getY() < t.getN_cols()) {
			if(t.getY() + 1 < t.getN_cols()) {
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
	
	
	
	public static List<Action> successor(State t) {
		List<Movement> moves = State.moveTractor(t);
		List<Action> actions = new ArrayList<Action>();
		List<int[]> combinations = generate_combinations(t.getMax());
		
		int [][] field = t.getField();
		int [] aux = new int[4];
		
		for (int i=0; i<moves.size(); i++) {
			for (int j=0; j<combinations.size(); j++) {
				int sum_sand = 0;
				
				for (int s=0; s<4; s++) {
					aux = combinations.get(j);
					sum_sand = sum_sand + aux[s];
				}
				
				t.setCurrent_sand(field[t.getX()][t.getY()] - t.getK());
				int sand = t.getCurrent_sand();
				
				if(sum_sand==sand && sum_sand>0) { //¿¿¿¿¿¿¿¿¿¿¿¿¿¿¿¿  sum_sand>0 ????????????
					if (move_Sand(t,aux)) {
						Movement mv = new Movement (moves.get(i).getX(), moves.get(i).getY());
						Action ac = new Action (mv, aux[0], aux[1], aux[2], aux[3]);
						actions.add(ac);
					}
				}
			}
		}
		
		return actions;
	}
		
	public static List<int[]> generate_combinations(int max) {
		List <int[]> combinations = new ArrayList<int[]> ();
		
	    for(int i=0; i<=max; i++) {
			for (int j=0; j<=max; j++) {
				for (int s=0; s<=max; s++) {
					for (int c=0; c<=max;c++) {
						int [] comb = {i,j,s,c};
						combinations.add(comb);
					}
				}
			}
	    }   
	    return combinations;
	}
	
	public static boolean move_Sand (State t, int [] sand) {
		int [][] field = t.getField();
		
		if (field[t.getX() - 1] [t.getY()] + sand [0] > t.getMax()) {
			return false;
		} else if (field[t.getX() + 1] [t.getY()] + sand [1] > t.getMax()) {
			return false;
		} else if (field[t.getX()] [t.getY() + 1] + sand [2] > t.getMax()) {
			return false;
		} else if (field[t.getX()] [t.getY() - 1] + sand [3] > t.getMax()) {
			return false;
		}
		
		return true;
	}
	
	public List<Node> createFrontier(){
		//Order criteria?¿
		return frontier;
	}
	

}
