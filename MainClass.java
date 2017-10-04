import java.io.*;
import java.util.*;

public class MainClass {
	
	public static void main (String[]args) throws FileNotFoundException {
		Tractor tr = new Tractor();
		Field f = new Field(); 
		readFile(tr, f);
	}
	
	
	public static void readFile (Tractor t, Field f) {
		Scanner read = new Scanner(System.in);
		System.out.println("Enter the name of the .txt");
		String file = read.nextLine() + ".txt";
		
		
		try {
			File fp = new File(file);
			FileReader fr = new FileReader(fp);
			BufferedReader bf = new BufferedReader(fr);
			int n_line = 0;
			String line;
			
			while ((line=bf.readLine()) != null) {
				String [] aux = line.split(" ");
				n_line++;
				switch(n_line) {
				case 1 :
					t.setX(Integer.parseInt(aux[0]));
					t.setY(Integer.parseInt(aux[1]));
					f.setK(Integer.parseInt(aux[2]));
					f.setMax(Integer.parseInt(aux[3]));
					f.setN_cols(Integer.parseInt(aux[4]));
					f.setN_rows(Integer.parseInt(aux[5]));
					
					break;
				
				default:
					
				}
				
				System.out.println(line);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
		
		read.close();
	}
}
