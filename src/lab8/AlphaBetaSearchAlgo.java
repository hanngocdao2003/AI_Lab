package lab8;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AlphaBetaSearchAlgo implements ISearchAlgo {

	// function ALPHA-BETA-SEARCH(state) returns an action
	// inputs: state, current state in game
	// v <- MAX-VALUE(state, Integer.MIN_VALUE , Integer.MAX_VALUE)
	// return the action in SUCCESSORS(state) with value v
	@Override
	public void execute(Node node) {
		// Enter your code here
		int result = maxValue(node, Integer.MIN_VALUE, Integer.MAX_VALUE);
		System.out.println("Alpha-Beta Result: " + result);

	}

	// function MAX-VALUE(state, alpha, beta) returns a utility value
	// if TERMINAL-TEST(state) then return UTILITY(state)
	// v <- MIN_VALUE;
	// for each s in SUCCESSORS(state) do
	// v <- MAX(v, MIN-VALUE(s, alpha, beta))
	// if v >= beta then return v
	// alpha <- MAX(alpha, v)
	// return v

	public int maxValue(Node node, int alpha, int beta) {
		// Enter your code here
		if (node.isTerminal())
			return node.getValue();
		int v = Integer.MIN_VALUE;
		List<Node> childs = node.getChildren();
		Collections.sort(childs, Node.LabelComparator);
		for (Node child : childs) {
			v = Math.max(v, minValue(child, alpha, beta));
			if (v >= beta)
				return v;
			else
				alpha = Math.max(alpha, v);
		}
		return v;
	}
	// function MIN-VALUE(state, alpha , beta) returns a utility value
	// if TERMINAL-TEST(state) then return UTILITY(state)
	// v <- Integer.MAX_VALUE
	// for each s in SUCCESSORS(state) do
	// v <- MIN(v, MAX-VALUE(s, alpha , beta))
	// if v <= alpha then return v
	// beta <- MIN(beta ,v)
	// return v

	public int minValue(Node node, int alpha, int beta) {
		// Enter your code here
		if (node.isTerminal())
			return node.getValue();
		int v = Integer.MAX_VALUE;
		List<Node> childs = node.getChildren();
		Collections.sort(childs, Node.LabelComparator);
		Collections.reverse(childs);
		for (Node child : childs) {
			v = Math.min(v, maxValue(child, alpha, beta));
			if (v <= alpha)
				return v;
			else
				beta = Math.min(beta, v);
		}
		return v;
	}
}
