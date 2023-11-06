package lab5;

import java.util.Arrays;
import java.util.List;

public class TestNode {

	public static void main(String[] args) {
		Puzzle p = new Puzzle();
		p.readInput("src\\lab5\\PuzzleMap.txt", "src\\lab5\\PuzzleGoalState.txt");

		Node initialState = p.getInitialState();
		Node tmp = new Node(initialState);
		System.out.println(initialState.equals(tmp));
		Node goalState = p.getGoalState();
		System.out.println(p.getInitialState());
		System.out.println("H: " + initialState.getH());
//		System.out.println(Arrays.toString(initialState.getWhiteTilePosition()));
		System.out.println(p.getGoalState());
		//
		Node re = p.moveWhiteTile(initialState, 'r');
		System.out.println(re);
		re = p.moveWhiteTile(initialState, 'l');
		System.out.println(re);
		re = p.moveWhiteTile(initialState, 'd');
		System.out.println(re);
		re = p.moveWhiteTile(initialState, 'u');
		System.out.println(re);
		//
		System.out.println(re.getH());
//		System.out.println(initialState);
//		System.out.println(Arrays.toString(re.getWhiteTilePosition()));
//		System.out.println(p.computeH(init, goal));

		System.out.println(p.getInitialState());
		System.out.println(p.getGoalState());
		//
		List<Node> children = p.getSuccessors(initialState);
		System.out.println("Size: " + children.size());
		for (Node child : children) {
			System.out.println(child.getH() + " " + p.computeH1(child));
		}
		System.out.println(p.computeH1(tmp));
		System.out.println(p.computeH2(tmp));

		System.out.println("--------------------------------------------");
		IPuzzleAlgo gbfs = new GreedyBestFirstSearch();
		Node result1 = gbfs.execute(p);

		System.out.println("Greedy Best First Search: \n" + result1);

		IPuzzleAlgo astar = new AStarSearch();
		Node result2 = astar.execute(p);

		System.out.println("A Star Search: \n" + result2);

	}
}
