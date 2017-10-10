import java.io.*;
import java.util.*;

public class FileHandler {
	public static void readFile (Tractor t, Field f) throws IOException, InputExceptions {
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
						f.setK(Integer.parseInt(aux[2]));
						f.setMax(Integer.parseInt(aux[3]));
						f.setN_cols(Integer.parseInt(aux[4]));
						f.setN_rows(Integer.parseInt(aux[5]));
						
					} catch (Exception e) {
						throw new InputExceptions("Letter");
					}
					
					aux_field = new int[f.getN_rows()][f.getN_cols()];
					
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
			
			f.setField(aux_field);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			bf.close();
		}
		
		read.close();
	}
	
	public static void writeFile (Field f) throws IOException {
		FileWriter fw = null;
		int [][] field = f.getField();
		
		try {
			
			fw = new FileWriter("newField.txt");
			int i = 0;
			
			do {
				for (int j=0; j<f.getN_cols(); j++) {
					fw.write(field[i][j] + " ");
				}
				fw.write(System.getProperty("line.separator"));
				i++;
			} while (i<f.getN_rows());
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			fw.close();
		}
	}
}
