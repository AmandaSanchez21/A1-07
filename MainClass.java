import java.io.*;
import java.util.*;

public class MainClass {
	
	public static void main (String[]args) throws FileNotFoundException {
		readFile();
	}
	
	
	public static void readFile () {
		Scanner read = new Scanner(System.in);
		System.out.println("Enter the name of the .txt");
		String file = read.nextLine() + ".txt";
		
		
		try {
			File fp = new File(file);
			FileReader fr = new FileReader(fp);
			BufferedReader bf = new BufferedReader(fr);
			
			
			String line;
			while ((line=bf.readLine()) != null) {
				System.out.println(line);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		
		read.close();
	}
}
