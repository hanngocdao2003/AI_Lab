package lab5;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class AStarSearch implements IPuzzleAlgo {

	@Override
	public Node execute(Puzzle model) {
		// TODO Auto-generated method stub
		PriorityQueue<Node> frontier = new PriorityQueue<>(PuzzleUtils.HeuristicComparatorByF);
		ArrayList<Node> explored = new ArrayList<>();

		Node initialState = model.getInitialState();
		initialState.setG(0);
		initialState.setH(model.computeH2(initialState));
		frontier.add(initialState);

		while (!frontier.isEmpty()) {
			Node currentNode = frontier.poll();

			if (currentNode.equals(model.getGoalState())) {
				return currentNode;
			} else {
				explored.add(currentNode);

				for (Node child : model.getSuccessors(currentNode)) {
					int tentativeG = currentNode.getG() + 1;

					if (!explored.contains(child) && !frontier.contains(child)) {
						child.setH(model.computeH2(currentNode));
						child.setG(currentNode.getG() + 1);
						frontier.add(child);
					} else if (frontier.contains(child) && child.getG() > tentativeG) {
						frontier.remove(child);
						child.setG(tentativeG);
						child.setH(model.computeH2(currentNode));
						frontier.add(child);
					}
				}
			}
		}
		return null;
	}

}
