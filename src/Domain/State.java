package Domain;

import java.util.*;

public class State {
	private int n_rows;
	private int n_cols;
	private int K;
	private int max;
	private int X;
	private int Y;
	private int[][] field;

	public State() {
		this.n_rows = 0;
		this.n_cols = 0;
		K = 0;
		this.max = 0;
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
		int[][] aux = new int[n_cols][n_rows];

		for (int i = 0; i < field.length; i++) {
			System.arraycopy(field[i], 0, aux[i], 0, field[i].length);
		}

		return aux;
	}

	public void setField(int[][] field) {
		this.field = field;
	}

	/**
	 * Method name: moveTractor Method Description: method that is going to return a list with the possible movements of the tractor.
	 * 
	 * @param t: state containing the necessary information to generate the movements.
	 * @return the list of possible movements.
	 */
	
	public static List<Movement> moveTractor(State t) {
		List<Movement> pos_moves = new ArrayList<Movement>();
		
		if (t.getX() - 1 >= 0) { //NORTH
			Movement n = new Movement(t.getX() - 1, t.getY());
			pos_moves.add(n);
		}	

		if (t.getY() - 1 >= 0) { //WEST
			Movement w = new Movement(t.getX(), t.getY() - 1);
			pos_moves.add(w);
		}
		
		if (t.getY() + 1 < t.getN_cols()) { //EAST
			Movement e = new Movement(t.getX(), t.getY() + 1);
			pos_moves.add(e);
		}
		
		if (t.getX() + 1 < t.getN_rows()) { //SOUTH
			Movement s = new Movement(t.getX() + 1, t.getY());
			pos_moves.add(s);
		}	
		
		return pos_moves;
	}
	
	
	
	/**
	 * Method name: successor
	 * Method Description: method in charge of generating all the possible actions for a given state.
	 * @param t: state that determines what actions can and cannot be taken.
	 * @return the list of possible actions. 
	 */

	public static List<Action> successor(State t) {
		List<Movement> moves = State.moveTractor(t);
		List<Action> actions = new ArrayList<Action>();
		int[][] field = t.getField();
		int sand = field[t.getX()][t.getY()] - t.getK();
		if (sand < 0) {
			sand = 0;
		}
		List<int[]> combinations = generate_combinations(sand);

		int[] aux = new int[4];

		for (int i = 0; i < moves.size(); i++) {
			for (int j = 0; j < combinations.size(); j++) {
				int sum_sand = 0;

				for (int s = 0; s < 4; s++) {
					aux = combinations.get(j);
					sum_sand = sum_sand + aux[s];
				}

				if (sum_sand == sand) {
					if (move_Sand(t, aux)) {
						Movement mv = new Movement(moves.get(i).getX(), moves.get(i).getY());
						Action ac = new Action(mv, aux[0], aux[1], aux[2], aux[3]);
						actions.add(ac);
					}
				}

			}

		}
		
		return actions;
	}

	/**
	 * Method name: generate_combinations
	 * Method description: method that generates a list of the possible combinations of sand for an action
	 * @param max: the maximum amount of sand that can be moved.
	 * @return the list of possible combinations.
	 */
	public static List<int[]> generate_combinations(int max) {
		List<int[]> combinations = new ArrayList<int[]>();

		for (int i = 0; i <= max; i++) {
			for (int j = 0; j <= max; j++) {
				for (int s = 0; s <= max; s++) {
					for (int c = 0; c <= max; c++) {
						int[] comb = { i, j, s, c };
						combinations.add(comb);
					}
				}
			}
		}
		return combinations;
	}

	
	/**
	 * Method name: moveSand
	 * Method Description: method to check if it is possible to move a determined amount of sand to N,E,W or S.
	 * @param t: State that determines if the sand can or cannot be moved.
	 * @param sand: array containing how much sand is going to be moved to N,E,W and S.
	 * @return true if is possible to move sand, false otherwise. 
	 */
	public static boolean move_Sand(State t, int[] sand) {
		int[][] field = t.getField();
		boolean value = true;

		if (sand[0] > 0) { //NORTH
			if (t.getX() - 1 >= 0 && field[t.getX() - 1][t.getY()] + sand[0] <= t.getMax()) {
				value = true;
			} else {
				return false;
			}
		}

		if (sand[1] > 0) { //WEST
			if (t.getY() - 1 >= 0 && field[t.getX()][t.getY() - 1] + sand[1] <= t.getMax()) {
				value = true;
			} else {
				return false;
			}
			
		}

		if (sand[2] > 0) { //EAST
			if (t.getY() + 1 < field.length && field[t.getX()][t.getY() + 1] + sand[2] <= t.getMax()) {
				value = true;
			} else {
				return false;
			}
		}

		if (sand[3] > 0) { //SOUTH
			if (t.getX() + 1 < field.length && field[t.getX() + 1][t.getY()] + sand[3] <= t.getMax()) {
				value = true;
			} else {
				return false;
			}
		}

		return value;
	}

	public static State copyState(State st, Action ac, int[][] new_field) {
		State newState = new State();

		newState.setField(new_field);
		newState.setX(ac.getNext_move().getX());
		newState.setY(ac.getNext_move().getY());
		newState.setK(st.getK());
		newState.setMax(st.getMax());
		newState.setN_cols(st.getN_cols());
		newState.setN_rows(st.getN_rows());

		return newState;
	}
	
	
	
	/**
	 * Method name: cost
	 * Method Description: method responsible for computing the cost of a given action. 
	 * @param ac: the action whose cost has to be determined. 
	 * @return the cost of the action. 
	 */

	public static int cost(Action ac) {
		int moved_sand = ac.getSand_e() + ac.getSand_n() + ac.getSand_s() + ac.getSand_w();
		int cost = moved_sand + 1;
		return cost;
	}

	public void printField() {
		for (int i = 0; i < n_rows; i++) {
			for (int j = 0; j < n_cols; j++) {
				System.out.print(field[i][j]);
			}
			System.out.println();
		}
	}

}
