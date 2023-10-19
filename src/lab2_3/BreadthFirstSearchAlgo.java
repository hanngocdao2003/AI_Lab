package lab2_3;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BreadthFirstSearchAlgo implements ISearchAlgo {
	// find the path from root node to the goal node
	// graph search
	public Node execute(Node root, String goal) {
		Queue<Node> queue = new LinkedList<>();
		List<Node> explored = new ArrayList<>();
		queue.add(root);
		while (!queue.isEmpty()) {
			Node children = queue.poll();
			if (children.getLabel().equals(goal)) {
				return children;
			}
			explored.add(children);
			List<Node> child = children.getChildrenNodes();
			for (Node ch : child) {
				if (!queue.contains(ch) && !explored.contains(ch)) {
					queue.add(ch);
					ch.setParent(children);
				}
			}
		}
		return null;
	}

	@Override
	public Node execute(Node root, String start, String goal) {
		Queue<Node> queue = new LinkedList<>();
		List<Node> explored = new ArrayList<>();
		boolean started = false;
		queue.add(root);
		while (!queue.isEmpty()) {
			Node children = queue.poll();
			if (children.getLabel().equals(start)) {
				queue.clear();
				explored.clear();
				children.setParent(null);
				started = true;
			}
			if (children.getLabel().equals(goal) && started) {
				return children;
			}
			explored.add(children);
			List<Node> child = children.getChildrenNodes();
			for (Node ch : child) {
				if (!queue.contains(ch) && !explored.contains(ch)) {
					queue.add(ch);
					ch.setParent(children);
				}
			}
		}
		return null;
	}
}