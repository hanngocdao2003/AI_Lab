package lab6;

public class Test {
	public static void main(String[] args) {
		Queen q1 = new Queen(0, 0);
		Queen q2 = new Queen(1, 1);
		Queen q3 = new Queen(2, 2);
		Queen q4 = new Queen(3, 3);
		Queen q5 = new Queen(4, 4);
		Queen q6 = new Queen(5, 5);
		Queen q7 = new Queen(6, 6);
		Queen q8 = new Queen(7, 7);

		Queen[] q = { q1, q2, q3, q4, q5, q6, q7, q8 };
		Node n = new Node(q);

		System.out.println(n.getH());

		n.displayBoard();
	}
}
