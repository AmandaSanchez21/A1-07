package persistance;
import java.io.*;
import java.util.*;

import Domain.State;

public class FileHandler {
	public static void readFile (State t) throws IOException, InputExceptions {
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
					try {
						t.setX(Integer.parseInt(aux[0]));
						t.setY(Integer.parseInt(aux[1]));
						t.setK(Integer.parseInt(aux[2]));
						t.setMax(Integer.parseInt(aux[3]));
						t.setN_cols(Integer.parseInt(aux[4]));
						t.setN_rows(Integer.parseInt(aux[5]));
						
					} catch (Exception e) {
						throw new InputExceptions("Letter");
					}
					
					aux_field = new int[t.getN_rows()][t.getN_cols()];
					
					break;
				
				default:
					int i = n_line-2;
					for (int j=0; j<aux_field.length;j++) {
						try {
							aux_field[i][j] = Integer.parseInt(aux[j+1]);
						} catch (Exception e) {
							throw new InputExceptions("Letter");
						}
					}
				}
			}
			
			t.setField(aux_field);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			bf.close();
		}
		
		read.close();
	}
	
	public static void writeFile (State t) throws IOException {
		FileWriter fw = null;
		int [][] field = t.getField();
		
		try {
			
			fw = new FileWriter("newField.txt");
			int i = 0;
			
			do {
				for (int j=0; j<t.getN_cols(); j++) {
					fw.write(field[i][j] + " ");
				}
				fw.write(System.getProperty("line.separator"));
				i++;
			} while (i<t.getN_rows());
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			fw.close();
		}
	}
}
