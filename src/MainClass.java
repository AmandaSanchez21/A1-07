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
