import java.util.ArrayList;
import java.util.List;

public class Action {
	private Movement next_move;
	private int sand_n, sand_s, sand_w, sand_e;
	
	public Action (Movement nm, int n, int s, int w, int e) {
		this.next_move = nm;
		this.sand_s = s;
		this.sand_n = n;
		this.sand_e = e;
		this.sand_w = w;
	}
	
	public Action () {
	
	}

	public Movement getNext_move() {
		return next_move;
	}

	public void setNext_move(Movement next_move) {
		this.next_move = next_move;
	}

	public int getSand_n() {
		return sand_n;
	}

	public void setSand_n(int sand_n) {
		this.sand_n = sand_n;
	}

	public int getSand_s() {
		return sand_s;
	}

	public void setSand_s(int sand_s) {
		this.sand_s = sand_s;
	}

	public int getSand_w() {
		return sand_w;
	}

	public void setSand_w(int sand_w) {
		this.sand_w = sand_w;
	}

	public int getSand_e() {
		return sand_e;
	}

	public void setSand_e(int sand_e) {
		this.sand_e = sand_e;
	}
	
	public static List<Action> decideActions(Tractor t, Field f) {
		List<Movement> moves = Tractor.moveTractor (t,f);
		List<Action> actions = new ArrayList<Action>();
		int n_combs = f.getMax()*1000 + f.getMax()*100 + f.getMax()*10 + f.getMax();
		List <int[]> combinations = new ArrayList<int[]> ();
		int [][] field = f.getField();
		int [] aux = new int[4];
		
		for (int i=0; i<=n_combs; i++) {     
			combinations.add(generate_combinations(i));
		}
		
		for (int i=0; i<moves.size(); i++) {
			for (int j=0; j<combinations.size(); j++) {
				int sum_sand = 0;
				
				for (int s=0; s<4; s++) {
					aux = combinations.get(j);
					sum_sand = sum_sand + aux[s];
				}
				
				t.setCurrent_sand(field[t.getX()][t.getY()] - f.getK());
				int sand = t.getCurrent_sand();
				
				if(sum_sand==sand && sum_sand>0) {
					Movement mv = new Movement (moves.get(i).getX(), moves.get(i).getY());
					Action ac = new Action (mv, aux[0], aux[1], aux[2], aux[3]);
					actions.add(ac);
				}
			}
		}
		
		return actions;
	}
	
	
	
	public static int[] generate_combinations(int num) {
		int[] comb = new int[4];
	    int i = 0;
	   
	     do {
	        comb[i] = (num % 10);
	        num = num/10;
	        i++;
	    } while(num > 0);
	     
	    return comb;
	}
	
	
}
