import java.io.*;
import java.util.*;

public class MainClass {
	
	public static void main (String[]args) throws IOException, InputExceptions {
		Tractor tr = new Tractor();
		Field f = new Field(); 
		FileHandler.readFile(tr, f);
		int [][]field = f.getField();
		
		for (int i1=0; i1<field.length; i1++) {
			for(int j=0; j<field.length; j++) {
				System.out.print(field[i1][j]);
			}
			System.out.println();
		}
		
		
		List<Movement> movements = moveTractor(tr,f);
		System.out.println("Possible movements: ");
		
		for (int i=0; i<movements.size(); i++) {
			movements.get(i).printMove();
		}
		
		List<Action> actions = decideActions(tr,f);
		System.out.println();
		/*for(int i=0; i<actions.size(); i++) {
			System.out.println("[(" + actions.get(i).getNext_move().getX() + "," + actions.get(i).getNext_move().getY() + ")"  + " N:" +
		actions.get(i).getSand_n() + " S:" + actions.get(i).getSand_s() + " E:" + actions.get(i).getSand_e() + " W:" + actions.get(i).getSand_w() + "]");
		}*/
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

	public static List<Action> decideActions(Tractor t, Field f) {
		List<Movement> moves = moveTractor (t,f);
		List<Action> actions = new ArrayList<Action>();
		int n_combs = f.getMax()*1000 + f.getMax()*100 + f.getMax()*10 + f.getMax();
		List <int[]> combinations = new ArrayList<int[]> ();
		int [][] field = f.getField();
		int [] aux = new int[4];
		
		for (int i=0; i<=n_combs; i++) {
			combinations.add(generate_combinations(i));
		}
		
		for(int i=0; i<combinations.size();i++) {
	    	int[] aux1 = combinations.get(i);
	    	for (int j=0; j<aux1.length;j++) {
	    		System.out.print(aux1[j]);
	    	}
	    	System.out.println();
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
