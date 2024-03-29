package lab9;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Node {
	private List<Integer> data = new ArrayList<Integer>();

	public void add(Integer val) {
		this.data.add(val);
	}

	public void addAll(List<Integer> data) {
		this.data.addAll(data);
	}

	// Get children of the current nodes
	public List<Node> getSuccessors() {
		// Enter your code here
		List<Node> successors = new ArrayList<>();

		for (int i = 0; i < data.size(); i++) {
			int current = data.get(i);
			for (int j = 1; j <= current / 2; j++) {
				if (j != (current - j)) {
					Node successor = new Node();
					successor.add(j);
					successor.add(current - j);
					for (int k = 0; k < data.size(); k++) {
						if (k != i) {
							successor.add(data.get(k));
						}
					}
					if (!successors.contains(successor)) {
						successors.add(successor);
					}
				}
			}
		}

		return successors;

	}

	// Check whether a node is terminal or not
	public boolean isTerminal() {
		// Enter your code here
		data.sort(DESCOMPARATOR);
		return data.get(0) <= 2;
	}

	public static final Comparator<Integer> DESCOMPARATOR = new Comparator<Integer>() {

		@Override
		public int compare(Integer o1, Integer o2) {
			return o2.compareTo(o1);
		}
	};

	@Override
	public String toString() {
		Collections.sort(this.data, DESCOMPARATOR);
		return this.data.toString();
	}
}
