package lab7;

public class Test {
	public static void main(String[] args) {
		GA_NQueenAlgo geneticAlgorithm = new GA_NQueenAlgo();

		Node solution = geneticAlgorithm.execute();

		System.out.println("Final Solution:");

		solution.displayBoard();
		System.out.println("H = " + solution.getH());
	}
}
