package Domain;
import java.io.*;
import java.util.*;

import persistance.FileHandler;
import persistance.InputExceptions;

public class Problem {

	public static void main (String[]args) throws IOException, InputExceptions {
		State t = new State(); 
		FileHandler.readFile(t);
		State newState = t; 
		int [][]field = t.getField();
		
		
		System.out.println();
		
		for (int i1=0; i1<field.length; i1++) {
			for(int j=0; j<field.length; j++) {
				System.out.print(field[i1][j]);
			}
			System.out.println();
		}
		
		List<Action>actions = State.successor(t);
		List<Movement> movements = State.moveTractor(t);
		System.out.println();
		System.out.println("Possible movements: ");
		
		for (int i=0; i<movements.size(); i++) {
			movements.get(i).printMove();
		}
		/*
		System.out.println();
		System.out.println();
		
		System.out.println("Possible actions: ");
		
		for(int i=0; i<actions.size(); i++) {
			System.out.println("[(" + actions.get(i).getNext_move().getX() + "," + actions.get(i).getNext_move().getY() + ")"  + " N:" +
		actions.get(i).getSand_n() + " S:" + actions.get(i).getSand_s() + " E:" + actions.get(i).getSand_e() + " W:" + actions.get(i).getSand_w() + "]");
		}*/
		
		
		String strategy;
		Scanner sc = new Scanner(System.in);
		System.out.println("Please, enter the strategy\nBFS, DFS, DLS, ILS or UCS ");
		strategy = sc.nextLine();
		strategy = strategy.toUpperCase();
		
		while (!strategy.equals("BFS") && !strategy.equals("DFS") && !strategy.equals("DLS") && !strategy.equals("ILS") && !strategy.equals("UCS")) {
			System.out.println("Please, enter a correct strategy \nBFS, DFS, DLS, ILS or UCS");
			strategy = sc.nextLine().toUpperCase();
		}
		
		System.out.println("Now, enter the depth desired.");
		int depth = sc.nextInt();
		
		while(depth<0) {
			System.out.println("Please, enter a valid depth");
			depth = sc.nextInt();
		}
		
		List<Node> solution = boundedSearch(t, strategy, depth);
		System.out.println(solution.size());
		
	}
	
	
	public static List<Node> boundedSearch(State st, String strategy, int max_depth) {
		PriorityQueue<Node> frontier = new PriorityQueue<Node>();
		int [][] field = st.getField();
		Node initial_node = new Node(st, 0, 0, null);
		initial_node.selectValueNode(strategy);
		frontier.add(initial_node);
		Node current_node = new Node();
		
		boolean solution = false;

		
		while(!frontier.isEmpty() && solution==false) {
			current_node = frontier.poll();
			
			if(isGoal(current_node.getState())) {
				solution = true;
			} else {
				List<Action> actions = State.successor(current_node.getState());
				List<Node> nodes = createNodeList(actions, current_node, max_depth, strategy, current_node.getState());
				
				for(int i=0; i<nodes.size(); i++) {
					frontier.add(nodes.get(i));
				}
			}
		}
		for (int i =0; i<field.length; i++) {
			for (int j=0; j<field.length; j++) {
				System.out.print(field[i][j]);
			}
			System.out.println();
		}
		if(solution) {
			return createSolution(current_node);
		} else {
			System.out.println("No solution");
			return null;
			
		}
		
	}
	
	
/*	public static List<Node> search (State st, String strategy, int max_depth, int inc_depth) {
		int current_depth = inc_depth;
		List<Node> solution = new ArrayList<Node>();
		
		while (solution.size() == 0 && current_depth <= max_depth) {
			solution = boundedSearch(st,strategy,current_depth);
			current_depth += inc_depth;
		}
		return solution;
	}*/
	
	
	


	public static boolean isGoal (State st) {
		int [][] field = st.getField();
		
		for (int i=0; i<field.length; i++) {
			for (int j=0; j<field.length; j++) {
				if(field[i][j] != st.getK()) {
					return false;
				}
			}
		}	
		return true;
	}
	
	public static State applyAction (State st, Action ac) {
		
		int [][] newField = st.getField();
		int pos = newField[st.getX()][st.getY()];
		int movedSand = ac.getSand_e() + ac.getSand_n() + ac.getSand_s() + ac.getSand_w();
		int newPos = pos - movedSand;
		newField[st.getX()][st.getY()] = newPos;
		//st.setCurrent_sand(st.getCurrent_sand() - movedSand);
		
	
		
		if(ac.getSand_n() > 0 && st.getX()-1 > 0 && newField[st.getX()-1][st.getY()] + ac.getSand_n() <= st.getMax()) {
			newField[st.getX()-1][st.getY()] += ac.getSand_n();
		} 
		if(ac.getSand_s() > 0 && st.getX()+1 < newField.length && newField[st.getX()+1][st.getY()] + ac.getSand_s() <= st.getMax()) {
			newField[st.getX()+1][st.getY()] += ac.getSand_s();
		} 
		if(ac.getSand_w() > 0 && st.getY()-1 > 0 && newField[st.getX()][st.getY()-1] + ac.getSand_w() <= st.getMax()) {
			newField[st.getX()][st.getY()-1] += ac.getSand_w();
		} 
		if(ac.getSand_e() > 0 && st.getY()+1 < newField.length && newField[st.getX()][st.getY()+1] + ac.getSand_e() <= st.getMax()) {
			newField[st.getX()][st.getY()+1] += ac.getSand_e();
		}
		
		/*System.out.println(ac.toString());
		for(int i=0; i<newField.length; i++) {
			for (int j=0; j<newField.length; j++) {
				System.out.print(" " + newField[i][j]);
			}
			System.out.println("");
		}*/
		State newState = State.copyState(st, ac, newField);

		return newState;
	}
	
	public static List<Node> createNodeList (List<Action> actions, Node cn, int depth, String strategy, State st) {
		List <Node> nodes = new ArrayList<Node>();
		int new_depth = cn.getDepth() +1;
		if(cn.getDepth() + 1 <= depth) {
			for(int i=0; i<actions.size(); i++) {
				State c_state = applyAction(st, actions.get(i));
				Node aux = new Node(c_state, State.cost(actions.get(i)), new_depth, cn);
				aux.selectValueNode(strategy);
				nodes.add(aux);
			}
		}
		
		return nodes;
	}
	
	
	private static List<Node> createSolution(Node current_node) {
		List <Node> solution = new ArrayList<Node>();
		
		while (current_node != null) {
			solution.add(current_node);
			current_node=current_node.getFather();
		}
		
		return solution;
	}

}