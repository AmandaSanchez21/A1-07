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
		
		
		List<Movement> movements = Tractor.moveTractor(tr,f);
		System.out.println();
		System.out.println("Possible movements: ");
		
		for (int i=0; i<movements.size(); i++) {
			movements.get(i).printMove();
		}
		
		List<Action> actions = Action.decideActions(tr,f);
		System.out.println();
		System.out.println();
		System.out.println("Possible actions: ");
		for(int i=0; i<actions.size(); i++) {
			System.out.println("[(" + actions.get(i).getNext_move().getX() + "," + actions.get(i).getNext_move().getY() + ")"  + " N:" +
		actions.get(i).getSand_n() + " S:" + actions.get(i).getSand_s() + " E:" + actions.get(i).getSand_e() + " W:" + actions.get(i).getSand_w() + "]");
		}
	}
	
	
	


	
	
}
