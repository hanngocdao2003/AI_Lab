package lab4;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class GreedyBestFirstSearchAlgo implements IInformedSearchAlgo {

	@Override
	public Node execute(Node root, String goal) {
		// TODO Auto-generated method stub
		PriorityQueue<Node> frontier = new PriorityQueue<Node>(new NodeComparator());
		ArrayList<Node> explored = new ArrayList<Node>();

		frontier.add(root);
		while (!frontier.isEmpty()) {
			Node currentNode = frontier.poll();
			if (currentNode.getLabel().equals(goal)) {
				return currentNode;
			} else {
				explored.add(currentNode);
				List<Edge> children = currentNode.getChildren();
				for (Edge child : children) {
					Node end = child.getEnd();
					if (!explored.contains(end) && !frontier.contains(end)) {
						end.setParent(currentNode);
						end.setG(currentNode.getG() + child.getWeight());
						frontier.add(end);
					}
				}
			}
		}
		return null;
	}

	@Override
	public Node execute(Node root, String start, String goal) {
		// TODO Auto-generated method stub
		return null;
	}

	class NodeComparator implements Comparator<Node> {
		@Override
		public int compare(Node o1, Node o2) {
			// TODO Auto-generated method stub
			int re = Double.compare(o1.getH(), o2.getH());
			if (re == 0) {
				return o1.getLabel().compareTo(o2.getLabel());
			}
			return re;
		}

	}

	@Override
	public boolean isAdmissibleH(Node root, String goal) {
		// TODO Auto-generated method stub
		return false;
	}
}
