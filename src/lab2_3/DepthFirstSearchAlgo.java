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

		stack.push(root);
		while (!stack.isEmpty()) {
			Node currentNode = stack.pop();
			explored.add(currentNode);

			if (currentNode.getLabel().equals(goal)) {
				return currentNode; // Trả về nút chứa mục tiêu
			}

			if (currentNode.getLabel().equals(start)) {
				start = null; // Đặt "start" thành null sau khi sử dụng nó
			}

			List<Node> childNodes = currentNode.getChildrenNodes();
			Collections.reverse(childNodes);

			for (Node child : childNodes) {
				if (!explored.contains(child) && !stack.contains(child)) {
					stack.push(child);
					child.setParent(currentNode);
				}
			}
		}
		return null;
	}

}
