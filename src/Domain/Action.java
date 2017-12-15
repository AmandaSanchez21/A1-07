package Domain;
public class Action {

	private Movement next_move;
	private int sand_n, sand_s, sand_w, sand_e;
	
	public Action (Movement nm, int n, int w, int e, int s) {
		this.next_move = nm;
		this.sand_s = s;
		this.sand_n = n;
		this.sand_e = e;
		this.sand_w = w;
	}

	public Movement getNext_move() {
		return next_move;
	}

	public void setNext_move(Movement next_move) {
		this.next_move = next_move;
	}

	public int getSand_n() {
		return sand_n;
	}

	public void setSand_n(int sand_n) {
		this.sand_n = sand_n;
	}

	public int getSand_s() {
		return sand_s;
	}

	public void setSand_s(int sand_s) {
		this.sand_s = sand_s;
	}

	public int getSand_w() {
		return sand_w;
	}

	public void setSand_w(int sand_w) {
		this.sand_w = sand_w;
	}

	public int getSand_e() {
		return sand_e;
	}

	public void setSand_e(int sand_e) {
		this.sand_e = sand_e;
	}
	
	
	
	
	@Override
	public String toString() {
		return "Action [sand_n=" + sand_n + ", sand_s=" + sand_s + ", sand_w=" + sand_w
				+ ", sand_e=" + sand_e + "]";
	}
	

}
