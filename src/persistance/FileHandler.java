package persistance;

import java.io.*;
import java.util.*;

import Domain.Node;
import Domain.State;

public class FileHandler {
	public static void readFile(State t) throws IOException, InputExceptions {
		Scanner read = new Scanner(System.in);
		System.out.println("Enter the name of the .txt");
		String file = read.nextLine() + ".txt";

		File fp = new File(file);
		BufferedReader bf = null;
		int n_line = 0;
		String line;
		int[][] aux_field = null;

		/* DETECT ERRORS IN THE FILE. LETTERS, NEGATIVE NUMBERS.... */

		try {

			FileReader fr = new FileReader(fp);
			bf = new BufferedReader(fr);

			while ((line = bf.readLine()) != null) {
				String[] aux = line.split(" ");
				n_line++;

				switch (n_line) {
				case 1:
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
					int i = n_line - 2;
					for (int j = 0; j < aux_field.length; j++) {
						try {
							aux_field[i][j] = Integer.parseInt(aux[j + 1]);
						} catch (Exception e) {
							throw new InputExceptions("Letter");
						}
					}
				}
			}

			t.setField(aux_field);

		} catch (FileNotFoundException e) {
			System.out.println("The file introduced can not be found.");
			System.exit(0);
		} finally {
			bf.close();
		}
	}

	public static void writeFile (List<Node> nodes, long time, int spatial_complexity, String strategy, boolean opt) throws IOException {
		FileWriter fw = null;
		int cost = 0;
		Node node;
		
		try {
			fw = new FileWriter("Solution.txt");	
			fw.write("The strategy used is: " + strategy);
			
			if (opt) {
				fw.write(" and optimization has been used.");
			} else {
				fw.write(" and optimization has not been used.");
			}
			
			fw.write(System.getProperty("line.separator"));
			
			for (int i = nodes.size() - 1; i >= 0; i--) {
				node = nodes.get(i);
	
				if (i == nodes.size() - 1) {
					int[][] field = node.getState().getField();
					fw.write(System.getProperty("line.separator"));
					fw.write("Initial Field: ");
					fw.write(System.getProperty("line.separator"));
					for(int x=0; x<field.length; x++) {
						for(int z=0; z<field.length; z++) {
							fw.write(Integer.toString(field[x][z]));
						}
						fw.write(System.getProperty("line.separator"));
					}
					fw.write(System.getProperty("line.separator"));
					fw.write("Initial Position: (" + node.getState().getX() + ", " + node.getState().getY() + ")");
					fw.write(System.getProperty("line.separator"));
				} else {
					cost += State.cost(node.getAction());
					State st = node.getState();
			
					fw.write(System.getProperty("line.separator"));
					fw.write(node.getAction().toString() + " Next move: " + node.getAction().getNext_move().printMove()
							+ " Cost of Action: " + State.cost(node.getAction()) + " Total cost " + cost);
					
					fw.write(System.getProperty("line.separator"));
					
					int[][] field = st.getField();
	
					for (int j = 0; j < field.length; j++) {
						for (int x = 0; x < field.length; x++) {
							fw.write(Integer.toString(field[j][x]));
						}
						fw.write(System.getProperty("line.separator"));
					}	
				}
			} 
			fw.write(System.getProperty("line.separator"));
			double time_s = time / 1000.00;
			fw.write("The total cost is " + cost + " and the total depth is " + nodes.get(0).getDepth());
			fw.write(System.getProperty("line.separator"));
			fw.write("Spatial complexity: " + spatial_complexity + ". Time complexity: " + time_s + " seconds.");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			fw.close();
		}
	}
}
