import java.io.*;
import java.util.*;

public class MainClass {
	
	public static void main (String[]args) throws IOException {
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
		
		moveTractor(tr,f);
		
		FileHandler.writeFile(f);
	}
	
	
	
	public static void moveTractor (Tractor t, Field f) {
		if (0 <= t.getX() && t.getX() < f.getN_rows()) {
			if(t.getX() + 1 < f.getN_rows()) {
				System.out.println("You can move to the south");
			}
			
			if(t.getX() - 1 >= 0) {
				System.out.println("You can move to the north");
			}
		}
		
		if (0 <= t.getY() && t.getY() < f.getN_cols()) {
			if(t.getY() + 1 < f.getN_cols()) {
				System.out.println("You can move to the right");
			}
			
			if(t.getY() - 1 >= 0) {
				System.out.println("You can move to the left");
			}
		}
	}
}
