package lab7;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class GA_NQueenAlgo implements IGeneticAlgo {

	public static final int POP_SIZE = 100;
	public static final double MUTATION_RATE = 0.03;
	public static final int MAX_ITERATIONS = 1000;
	List<Node> population = new ArrayList<Node>();
	Random rd = new Random();

	// initialize the individuals of the population
	public void initPopulation() {
		for (int i = 0; i < POP_SIZE; i++) {
			Node ni = new Node();
			ni.generateBoard();
			population.add(ni);
		}
	}

	public Node execute() {
		// Enter your code here
		initPopulation();
		int k = 0;
		while (k++ < MAX_ITERATIONS) {
			List<Node> newPopulation = new ArrayList<Node>();
			for (int i = 0; i < POP_SIZE; i++) {
				Node x = getParentByRandomSelection();
				Node y = getParentByRandomSelection();
				Node child = reproduce(x, y);
				if (rd.nextDouble() < MUTATION_RATE) {
					mutate(child);
				}
				if (child.getH() == 0) {
					return child;
				}
				newPopulation.add(child);
			}
			population = newPopulation;
		} // end while
		Collections.sort(population);
		return population.get(0);
	}

	// Mutate an individual by selecting a random Queen and
	// move it to a random row.
	public void mutate(Node node) {
		// Enter your code here
		if (rd.nextDouble() < MUTATION_RATE) {
			int queenIndex = rd.nextInt(Node.N);
			int newRow = rd.nextInt(Node.N);
			node.setRow(queenIndex, newRow);
		}
	}

	// Crossover x and y to reproduce a child
	public Node reproduce(Node x, Node y) {
		// Enter your code here
		Node child = new Node();
		child.generateBoard();
		int c = rd.nextInt(Node.N);
		for (int i = 0; i < c; i++) {
			child.setRow(i, x.getRow(i));
		}
		for (int i = c + 1; i < Node.N; i++) {
			child.setRow(i, y.getRow(i));
		}

		return child;
	}

	// Select K individuals from the population at random and
	// select the best out of these to become a parent.
	public Node getParentByTournamentSelection(int k) {
		// Enter your code here
		Random rd = new Random();
		Node bestParent = null;
		int bestH = Integer.MAX_VALUE;

		for (int i = 0; i < k; i++) {
			Node randomParent = population.get(rd.nextInt(POP_SIZE));
			int randomParentH = randomParent.getH();

			if (randomParentH < bestH) {
				bestH = randomParentH;
				bestParent = randomParent;
			}
		}

		return bestParent;
	}

	// Select a random parent from the population
	public Node getParentByRandomSelection() {
		// Enter your code here
		return population.get(rd.nextInt(POP_SIZE));

	}

}
