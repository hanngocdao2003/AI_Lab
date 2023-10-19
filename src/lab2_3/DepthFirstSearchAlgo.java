package lab2_3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class DepthFirstSearchAlgo implements ISearchAlgo {

	@Override
	public Node execute(Node root, String goal) {
		Stack<Node> stack = new Stack<>();
		List<Node> explored = new ArrayList<>();
		stack.add(root);
		while (!stack.isEmpty()) {
			Node children = stack.pop();
			if (children.getLabel().equals(goal)) {
				return children;
			}

			explored.add(children);
			List<Node> child = children.getChildrenNodes();
			Collections.reverse(child);
			for (Node ch : child) {
				if (!stack.contains(ch) && !explored.contains(ch)) {
					stack.push(ch);
					ch.setParent(children);
				}
			}
		}
		return null;
	}

	@Override
	public Node execute(Node root, String start, String goal) {
		Stack<Node> stack = new Stack<>();
		List<Node> explored = new ArrayList<>();
		boolean started = false;
		stack.add(root);
		while (!stack.isEmpty()) {
			Node children = stack.pop();
			if (children.getLabel().equals(start)) {
				stack.clear();
				explored.clear();
				children.setParent(null);
				started = true;
			}
			if (children.getLabel().equals(goal) && started) {
				return children;
			}
			explored.add(children);
			List<Node> child = children.getChildrenNodes();
			Collections.reverse(child);
			for (Node ch : child) {
				if (!stack.contains(ch) && !explored.contains(ch)) {
					stack.push(ch);
					ch.setParent(children);
				}
			}
		}
		return null;
	}

}
