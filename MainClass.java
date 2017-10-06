import java.io.*;
import java.util.*;

public class MainClass {
	
	public static void main (String[]args) throws IOException {
		Tractor tr = new Tractor();
		Field f = new Field(); 
		readFile(tr, f);
		
		int [][] field = f.getField();
		/*System.out.print(field.length);
		for(int i=0; i<field.length; i++) {
			for (int j=0; j<field.length; i++) {
				System.out.print(field[i][j]);
			}
		}*/
	}
	
	
	public static void readFile (Tractor t, Field f) throws IOException {
		Scanner read = new Scanner(System.in);
		System.out.println("Enter the name of the .txt");
		String file = read.nextLine() + ".txt";
		
		File fp = new File(file);
		BufferedReader bf = null;
		int n_line = 0;
		String line;
		int [][] aux_field = null;
		
		/*DETECT ERRORS IN THE FILE. LETTERS, NEGATIVE NUMBERS....*/
		
		try {
			FileReader fr = new FileReader(fp);
			bf = new BufferedReader(fr);
			
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
					
					aux_field = new int[f.getN_rows()][f.getN_cols()];
					for (int i=0; i<aux_field.length; i++) {
						for(int j=0; j<aux_field.length; j++) {
							aux_field[i][j] = 0;
						}
					}
					
					break;
				
				default:
					int i = n_line-2;
					for (int j=0; j<aux_field.length;j++) {
						aux_field[i][j] = Integer.parseInt(aux[j+1]);
					}
				}
			}
			
			for (int i1=0; i1<aux_field.length; i1++) {
				for(int j=0; j<aux_field.length; j++) {
					System.out.print(aux_field[i1][j]);
				}
				System.out.println();
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			bf.close();
		}
		
		read.close();
	}
}
