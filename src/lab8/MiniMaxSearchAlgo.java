package lab8;

import java.util.Collections;
import java.util.List;

public class MiniMaxSearchAlgo implements ISearchAlgo {

	// function MINIMAX-DECISION(state) returns an action
	// inputs: state, current state in game
	// v <- MAX-VALUE(state)
	// return the action in SUCCESSORS(state) with value v
	@Override
	public void execute(Node node) {
		// Enter your code here
		int re = maxValue(node);
		System.out.println("Value at " + node.getLabel() + ": " + re);

		// Print values at child nodes
		for (Node child : node.getChildren()) {
			int childValue = minValue(child);
			System.out.println("Value at " + child.getLabel() + ": " + childValue);
		}
	}

	// function MAX-VALUE(state) returns a utility value
	// if TERMINAL-TEST(state) then return UTILITY(state)
	// v <- Integer.MIN_VALUE
	// for each s in SUCCESSORS(state) do
	// v <- MAX(v, MIN-VALUE(s))
	// return v
	public int maxValue(Node node) {
		// Enter your code here
		if (node.isTerminal())
			return node.getValue();
		int v = Integer.MIN_VALUE;
		List<Node> childs = node.getChildren();
		Collections.sort(childs, Node.LabelComparator);
		for (Node child : childs) {
			v = Math.max(v, minValue(child));
		}
		return v;

	}
	// function MIN-VALUE(state) returns a utility value
	// if TERMINAL-TEST(state) then return UTILITY(state)
	// v <- Integer.MAX_VALUE
	// for each s in SUCCESSORS(state) do
	// v <- MIN(v, MAX-VALUE(s))
	// return v

	public int minValue(Node node) {
		// Enter your code here
		if (node.isTerminal())
			return node.getValue();
		int v = Integer.MAX_VALUE;
		List<Node> childs = node.getChildren();
		Collections.sort(childs, Node.LabelComparator);
		for (Node child : childs) {
			v = Math.min(v, maxValue(child));
		}
		return v;
	}
}
