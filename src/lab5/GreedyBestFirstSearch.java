package lab5;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class GreedyBestFirstSearch implements IPuzzleAlgo {

	@Override
	public Node execute(Puzzle model) {
		// TODO Auto-generated method stub
		PriorityQueue<Node> frontier = new PriorityQueue<>(PuzzleUtils.HeuristicComparatorByH);
		ArrayList<Node> explored = new ArrayList<>();

		frontier.add(model.getInitialState());

		while (!frontier.isEmpty()) {
			Node currentNode = frontier.poll();

			if (currentNode.equals(model.getGoalState())) {
				return currentNode;
			} else {
				explored.add(currentNode);

				List<Node> children = model.getSuccessors(currentNode);
				for (Node child : children) {
					if (!explored.contains(child) && !frontier.contains(child)) {
						child.setH(model.computeH2(child));
						child.setG(currentNode.getG() + 1);
						frontier.add(child);
					}
				}
			}
		}

		return null;
	}

}
