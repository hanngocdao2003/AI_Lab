package lab6;

public class HillClimbingSearchNQueen implements ILocalSearchAlgo {

	int stepClimbed = 0;
	int stepClimbedAfterRandomRestart = 0;
	int randomRestarts = 0;

	public Node execute(Node initialState) {
		Node current = initialState;
		Node neighbor = null;
		while (true) {
			neighbor = current.selectNextRandomCandidate(); // H min
			if (current.getH() > neighbor.getH()) {
				stepClimbed++;
				stepClimbedAfterRandomRestart++;
				current = neighbor;
			} else {
				return current;
			}
		}
	}

	public Node executeHillClimbingWithRandomRestart(Node initialState) {
		// Enter your code here.
		return null;
	}
}
