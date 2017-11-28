package Domain;

import java.util.Random;

public class Node implements Comparable<Node>{

	private State state;
	private int cost, depth, value;
	private Action action;
	private Node father;

	public Node () {
		this.state = null;
		this.cost = 0;
		this.depth = 0;
		
	}
	
	
	
	
	public Node(State state, int cost, int depth, Node father) {
		super();
		this.state = state;
		this.cost = cost;
		this.depth = depth;
		this.father = father;

	}




	public State getState() {
		return state;
	}
	public void setState(State state) {
		this.state = state;
	}
	
	
	public int getCost() {
		return cost;
	}
	public void setCost(int cost) {
		this.cost = cost;
	}
	
	
	public int getDepth() {
		return depth;
	}
	public void setDepth(int depth) {
		this.depth = depth;
	}
	
	
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	
	
	public Action getAction() {
		return action;
	}
	public void setAction(Action action) {
		this.action = action;
	}
	
	
	public Node getFather() {
		return father;
	}
	public void setFather(Node father) {
		this.father = father;
	}
	
	@Override
	public String toString() {
		return "Node [state=" + state + ", cost=" + cost + ", depth=" + depth + ", value=" + value + ", action="
				+ action + ", father=" + father + "]";
	}
	
	
	
	public void selectValueNode(String strategy) {
		if(strategy.equals("BFS")) {
			this.value = this.depth;
		} else if(strategy.equals("DFS") || strategy.equals("DLS") || strategy.equals("IDS")) {
			this.value = this.depth * -1;
		} else if (strategy.equals("UCS")) {
			this.value = this.cost;
		} else if (strategy.equals("A*")) {
			this.value = cost + heuristic(this.state);
		}
	}
	
	public int heuristic(State st) {
		int h=0;
		int [][] field = st.getField();
		
		
		for (int i=0; i<field.length; i++) {
			for (int j=0; j<field.length; j++) {
				if(field[i][j] != st.getK()) {
					h++;
				}
			}
		}
		return h;
	}
	
	
	public int compareTo (Node n) {
		if(this.getValue() < n.getValue()) {
			return -1;
		} else {
			return 1;
		}
	}
	
	

}
