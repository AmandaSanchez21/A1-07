
public class Action {
	private Movement next_move, move_s, move_n, move_w, move_e;
	private int sand_n, sand_s, sand_w, sand_e;
	
	public Action (Movement nm, int n, int s, int w, int e) {
		this.next_move = nm;
		this.sand_s = s;
		this.sand_n = n;
		this.sand_e = e;
		this.sand_w = w;
	}
	
	public Action () {
	
	}

	public Movement getNext_move() {
		return next_move;
	}

	public void setNext_move(Movement next_move) {
		this.next_move = next_move;
	}

	public Movement getMove_s() {
		return move_s;
	}

	public void setMove_s(Movement move_s) {
		this.move_s = move_s;
	}

	public Movement getMove_n() {
		return move_n;
	}

	public void setMove_n(Movement move_n) {
		this.move_n = move_n;
	}

	public Movement getMove_w() {
		return move_w;
	}

	public void setMove_w(Movement move_w) {
		this.move_w = move_w;
	}

	public Movement getMove_e() {
		return move_e;
	}

	public void setMove_e(Movement move_e) {
		this.move_e = move_e;
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
	
	
}
