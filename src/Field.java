
public class Field {
	private int n_rows, n_cols, K, max;
	private int [][] field;
	
	public Field () {
		this.n_rows = 0;
		this.n_cols = 0;
		this.K = 0;
		this.max = 0;
	}

	public int getN_rows() {
		return n_rows;
	}

	public void setN_rows(int n_rows) {
		this.n_rows = n_rows;
	}

	public int[][] getField() {
		return field;
	}

	public void setField(int[][] field) {
		this.field = field;
	}

	public int getN_cols() {
		return n_cols;
	}

	public void setN_cols(int n_cols) {
		this.n_cols = n_cols;
	}

	public int getK() {
		return K;
	}

	public void setK(int k) {
		K = k;
	}

	public int getMax() {
		return max;
	}

	public void setMax(int max) {
		this.max = max;
	}
}
