package lab6;

public interface ILocalSearchAlgo {
	public Node execute(Node intinialState);

	public Node executeHillClimbingWithRandomRestart(Node initialState);
}
