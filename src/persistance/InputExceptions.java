package persistance;

public class InputExceptions extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String type;
	public InputExceptions(String type) {
		this.type = type;
	};
	
	public String getMessage() {
		switch (type) {
		
		case "Letter": 
			return "Wrong format. There must be only integers in the file.";
		case "Blank space":
			return "Wrong format. The representation of the field has to start with a blank space.";
		case "Negative":
			return "Wrong format. The integers has to be positive.";
		default: 
			return "Unknown error.";
		}
	}

}
