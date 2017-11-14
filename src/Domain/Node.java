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
		
		Random rm = new Random();
		this.value = rm.nextInt(10000) + 1;
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
	
	
	
	public void selectValueNode(String strategy) {
		if(strategy == "BFS") {
			value = depth;
		} else if(strategy == "DFS" || strategy == "DLS" || strategy == "IDS") {
			value = -depth;
		} else if (strategy == "UCS") {
			value = cost;
		}
	}

	
	
	public int compareTo (Node n) {
		if(this.getValue() < n.getValue()) {
			return -1;
		} else {
			return 1;
		}
	}
	
	
	

}
