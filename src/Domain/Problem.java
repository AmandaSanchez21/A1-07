/***
 * Class Name: Problem
 * Class Authors: Amanda Sánchez García & Fernando Velasco Alba
 * Class Description: Class that implements the main functionality of the program
 */

package Domain;

import java.io.*;
import java.util.*;

import persistance.FileHandler;
import persistance.InputExceptions;

public class Problem {

	private static int spatial_complexity;
	private static long time_complexity;
	private static Hashtable<String, Integer> visited = new Hashtable<String, Integer>();

	/**
	 * Method name: main Method Description: method where the initial state is read
	 * from a file and where the different options to execute the program are
	 * captured.
	 * 
	 * @param args
	 * @throws IOException
	 * @throws InputExceptions
	 */

	public static void main(String[] args) throws IOException, InputExceptions {
		State t = new State();
		FileHandler.readFile(t);
		int[][] field = t.getField();
		boolean optimization;

		System.out.println();

		for (int i1 = 0; i1 < field.length; i1++) {
			for (int j = 0; j < field.length; j++) {
				System.out.print(field[i1][j]);
			}
			System.out.println();
		}
		System.out.println();
		String strategy;
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		System.out.println("Please, enter the strategy\nBFS, DFS, DLS, IDS, UCS or A*");
		strategy = sc.nextLine();
		strategy = strategy.toUpperCase();

		while (!strategy.equals("BFS") && !strategy.equals("DFS") && !strategy.equals("DLS") && !strategy.equals("IDS")
				&& !strategy.equals("UCS") && !strategy.equals("A*")) {
			System.out.println("Please, enter a correct strategy \nBFS, DFS, DLS, IDS, UCS or A*");
			strategy = sc.nextLine().toUpperCase();
		}

		System.out.println("Now, enter the depth desired.");
		int depth = sc.nextInt();

		while (depth <= 0) {
			System.out.println("Please, enter a valid depth");
			depth = sc.nextInt();
		}

		System.out.println("Do you want to use optimization? (Y/N)");
		String opt = sc.next().toUpperCase();

		while (!opt.equals("Y") && !opt.equals("N")) {
			System.out.println("Please, enter a valid option (Y/N)");
			opt = sc.next().toUpperCase();
		}

		if (opt.equals("Y")) {
			optimization = true;
		} else {
			optimization = false;
		}

		long time = System.currentTimeMillis();
		List<Node> solution = boundedSearch(t, strategy, depth, optimization);
		long time1 = System.currentTimeMillis();
		time_complexity = time1 - time;

		if (solution != null) {
			printSolution(solution);

			System.out.println();
			System.out.println("Do you wanna to keep the solution into a file? (Y/N)");
			String f = sc.next().toUpperCase();

			while (!f.equals("Y") && !f.equals("N")) {
				System.out.println("Please, enter a valid option (Y/N)");
				f = sc.next().toUpperCase();
			}

			if (f.equals("Y")) {
				FileHandler.writeFile(solution, time_complexity, spatial_complexity, strategy, optimization);
			}
		}

	}

	/**
	 * Method name: boundedSearch Method Description: method in charge of executing
	 * the main functionality of the program, which is look for a solution for the
	 * given field.
	 * 
	 * @param st:
	 *            initial state containing the necessary information to look for a
	 *            solution.
	 * @param strategy:
	 *            strategy used in order to find the solution.
	 * @param max_depth:
	 *            the maximum depth that is going to be used to look for the
	 *            solution.
	 * @param opt:
	 *            boolean indicating if the user wants to look for the solution
	 *            using optimization or not.
	 * @return solution: list of nodes which leads to the solution.
	 * @throws IOException
	 */

	public static List<Node> boundedSearch(State st, String strategy, int max_depth, boolean opt) throws IOException {
		PriorityQueue<Node> frontier = new PriorityQueue<Node>();
		Node initial_node = new Node(st, 0, 0, null); // Initial node with the initial state, cost and depth are 0 and
														// there's no reference to a parent
		initial_node.selectValueNode(strategy);
		frontier.offer(initial_node);
		spatial_complexity = 1;
		Node current_node = new Node();

		if (strategy.equals("BFS") || strategy.equals("DFS") || strategy.equals("DLS") || strategy.equals("IDS")) {
			visited.put(encryptState(initial_node.getState()), initial_node.getCost());
		} else {
			visited.put(encryptState(initial_node.getState()), initial_node.getValue());
		}

		boolean solution = false;

		while (!frontier.isEmpty() && solution == false) {
			current_node = frontier.poll(); // Removes the node which is in the head of the queue

			if (isGoal(current_node.getState())) {
				solution = true;
			} else {
				List<Action> actions = State.successor(current_node.getState()); // Generates all the possible actions
				List<Node> nodes = createNodeList(actions, current_node, max_depth, strategy, current_node.getState()); // Creates
																														// a
																														// node
																														// list
																														// where
																														// each
																														// node
																														// has
																														// a
																														// new
																														// state,
																														// generated
																														// after
																														// applying
																														// the
																														// corresponding
																														// action

				if (opt) {
					checkVisited(nodes, strategy);
				}

				for (int i = 0; i < nodes.size(); i++) {
					spatial_complexity += 1;
					frontier.offer(nodes.get(i));
				}
			}
		}

		if (solution) {
			return createSolution(current_node);
		} else {
			System.out.println("No solution");
			return null;
		}
	}

	/**
	 * Method name: checkVisited Method Description: method used in the optimized
	 * version of the program in order to check if a state has been previously
	 * visited.
	 * 
	 * @param nodes:
	 *            list of nodes, which include the states that have to be checked.
	 * @param strategy:
	 *            strategy that is going to be used to find the solution.
	 */

	public static void checkVisited(List<Node> nodes, String strategy) {
		for (int i = 0; i < nodes.size(); i++) {
			if (visited.containsKey(encryptState(nodes.get(i).getState()))) {
				if (strategy.equals("BFS") || strategy.equals("DFS") || strategy.equals("DLS")
						|| strategy.equals("IDS")) {
					if (nodes.get(i).getCost() < visited.get(encryptState(nodes.get(i).getState()))) {
						visited.replace((encryptState(nodes.get(i).getState())), nodes.get(i).getCost());
					} else {
						nodes.remove(i);
					}
				} else {
					if (nodes.get(i).getValue() < visited.get(encryptState(nodes.get(i).getState()))) {
						visited.replace((encryptState(nodes.get(i).getState())), nodes.get(i).getValue());
					} else {
						nodes.remove(i);
					}
				}
			} else {
				if (strategy.equals("BFS") || strategy.equals("DFS") || strategy.equals("DLS")
						|| strategy.equals("IDS")) {
					visited.put(encryptState(nodes.get(i).getState()), nodes.get(i).getCost());
				} else {
					visited.put(encryptState(nodes.get(i).getState()), nodes.get(i).getValue());
				}
			}
		}
	}

	/**
	 * Method name: isGoal Method description: method that check if an state is the
	 * goal state.
	 * 
	 * @param st:
	 *            state which has to be checked.
	 * @return true if the state is a goal state, false otherwise.
	 */
	public static boolean isGoal(State st) {
		int[][] field = st.getField();

		for (int i = 0; i < field.length; i++) {
			for (int j = 0; j < field.length; j++) {
				if (field[i][j] != st.getK()) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * Method name: applyAction Method Description: method which generates a new
	 * state after applying an action previously generated.
	 * 
	 * @param st:
	 *            state on which the action should be applied.
	 * @param ac:
	 *            action to be applied.
	 * @return the new State generated.
	 */

	public static State applyAction(State st, Action ac) {

		int[][] newField = st.getField();
		int pos = newField[st.getX()][st.getY()]; // Amount of sand in the current position
		int movedSand = ac.getSand_e() + ac.getSand_n() + ac.getSand_s() + ac.getSand_w(); // Totally amount of sand to
																							// move
		int newPos = pos - movedSand;
		newField[st.getX()][st.getY()] = newPos; // Remaining sand in the current position
		/*
		 * If there is sand to move to the North, East, West or South && if it is
		 * possible to move to the North, East, West or South
		 */
		if (ac.getSand_n() > 0 && st.getX() + 1 >= 0) {
			newField[st.getX() - 1][st.getY()] += ac.getSand_n(); // Update the amount of sand of the field
		}
		if (ac.getSand_s() > 0 && st.getX() + 1 < newField.length) {
			newField[st.getX() + 1][st.getY()] += ac.getSand_s();
		}
		if (ac.getSand_w() > 0 && st.getY() - 1 >= 0) {
			newField[st.getX()][st.getY() - 1] += ac.getSand_w();
		}
		if (ac.getSand_e() > 0 && st.getY() + 1 < newField.length) {
			newField[st.getX()][st.getY() + 1] += ac.getSand_e();
		}

		State newState = State.copyState(st, ac, newField);

		return newState;
	}

	/**
	 * Method name: createNodeList Method Description: method which generates the
	 * list of nodes resulting after applying all the possible actions for a state.
	 * 
	 * @param actions:list of actions which are going to be applied.
	 * @param cn:current node, which is going to be the parent of the nodes generated.
	 * @param depth:maximum depth to look for the solution.
	 * @param strategy: strategy used to find the solution.
	 * @param st: State on which the actions should be applied.
	 * @return list of nodes generated.
	 */
	public static List<Node> createNodeList(List<Action> actions, Node cn, int depth, String strategy, State st) {
		List<Node> nodes = new ArrayList<Node>();
		int new_depth = cn.getDepth() + 1;
		if (cn.getDepth() + 1 <= depth) {
			for (int i = 0; i < actions.size(); i++) {
				State c_state = applyAction(st, actions.get(i));
				Node aux = new Node(c_state, cn.getCost() + State.cost(actions.get(i)), new_depth, cn);
				aux.selectValueNode(strategy);
				aux.getState().setValue(aux.getValue());
				aux.setAction(actions.get(i));
				nodes.add(aux);
			}
		}

		return nodes;
	}

	/**
	 * Method name: createSolution Method Description: method which is going to
	 * return the list of nodes that leads to the solution.
	 * @param current_node:node in which the solution is found.
	 * @return the list of nodes leading to the solution.
	 */
	public static List<Node> createSolution(Node current_node) {
		List<Node> solution = new ArrayList<Node>();

		while (current_node != null) {
			solution.add(current_node);
			current_node = current_node.getFather();
		}

		return solution;
	}

	/**
	 * Method name: printSolution Method Description: method which is responsible of
	 * printing the solution of the problem.
	 * @param solution: list of nodes which leads to the solution.
	 */
	public static void printSolution(List<Node> solution) {
		int cost = 0;
		for (int i = solution.size() - 1; i >= 0; i--) {
			Node node = solution.get(i);

			if (i == solution.size() - 1) {
				System.out.println("Initial Field: ");
				node.getState().printField();
				System.out.println();
				System.out.println("Initial Position: (" + node.getState().getX() + ", " + node.getState().getY() + ")");
				System.out.println();
			} else {
				cost += State.cost(node.getAction());
				State st = node.getState();

				System.out.println(node.getAction().toString() + " Next move: " + node.getAction().getNext_move().printMove()
								+ " Cost of Action: " + State.cost(node.getAction()) + " Total cost " + cost);

				int[][] field = st.getField();

				for (int j = 0; j < field.length; j++) {
					for (int x = 0; x < field.length; x++) {
						System.out.print(field[j][x]);
					}
					System.out.println();
				}
				System.out.println();
			}
		}
		double time = time_complexity / 1000.00;
		System.out.println("The total cost is " + cost + " and the total depth is " + solution.get(0).getDepth()
				+ "\nSpatial complexity: " + spatial_complexity + ". Time complexity: " + time + " seconds.");
	}
	
	

	/**
	 * Method name: encryptState Method Description: method in charge of encrypting
	 * a given state.
	 * @param st:State to be encrypted.
	 * @return the String which is the key of the encrypted State.
	 */

	public static String encryptState(State st) {

		String key = "$" + Integer.toString(st.getX()) + Integer.toString(st.getY());

		for (int i = 0; i < st.getField().length; i++) {
			for (int j = 0; j < st.getField().length; j++) {
				key += Integer.toString(st.getField()[i][j]);
			}
		}

		return key;
	}
}