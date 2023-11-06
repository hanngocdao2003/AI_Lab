package lab5;

import java.awt.Taskbar.State;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Random;

public class Puzzle {
	public static final int MAX_ROW = 3;// 3x3: Dimension of the puzzle map
	public static final int MAX_COL = 3;
	public static final char[] operators = { 'l', 'r', 'u', 'd' };

	private Node initialState;
	private Node goalState;

	public Puzzle() {
		this.initialState = new Node(MAX_ROW, MAX_COL);
		this.goalState = new Node(MAX_ROW, MAX_COL);
	}

	// Load initial state and goal state from files
	public void readInput(String INITIAL_STATE_MAP_PATH, String GOAL_STATE_MAP_PATH) {
		try {
			// 1 - Import map
			BufferedReader bufferedReader = new BufferedReader(new FileReader(INITIAL_STATE_MAP_PATH));

			String line = null;
			int row = 0;
			while ((line = bufferedReader.readLine()) != null) {
				String[] tile = line.split(" ");
				for (int col = 0; col < tile.length; col++) {
					initialState.updateTile(row, col, Integer.parseInt(tile[col]));
				}
				row++;
			}

			bufferedReader.close();

			// 2 - Import goal state
			bufferedReader = new BufferedReader(new FileReader(GOAL_STATE_MAP_PATH));

			line = null;
			row = 0;
			while ((line = bufferedReader.readLine()) != null) {
				String[] tile = line.split(" ");
				for (int col = 0; col < tile.length; col++) {
					goalState.updateTile(row, col, Integer.parseInt(tile[col]));
				}
				row++;
			}

			bufferedReader.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// The total number of misplaced tiles
	public int computeH1(Node currentState) {
		int output = 0;
		/* Enter your code here */
		for (int i = 0; i < MAX_ROW; i++) {
			for (int j = 0; j < MAX_COL; j++) {
				int tileCurrent = currentState.getTile(i, j);
				int tileGoal = goalState.getTile(i, j);
				if (tileCurrent != tileGoal && tileCurrent != 0) {
					output++;
				}
			}
		}

		return output;
	}

	// Using manhattanDistance above to compute H
	public int computeH2(Node currentState) {
		int result = 0;
		/* Enter your code here */
		for (int i = 1; i < MAX_ROW * MAX_COL; i++) {
			int[] currentPos = currentState.getLocation(i);
			int[] goalPos = goalState.getLocation(i);
			result += PuzzleUtils.manhattanDistance(currentPos, goalPos);
		}
		return result;
	}

	public Node moveWhiteTile(Node currentState, char operator) {
	    Node result = new Node(currentState);
	    int[] whiteTile = currentState.getLocation(0); // Lấy vị trí ô trống
	    int row = whiteTile[0];
	    int col = whiteTile[1];

	    if (operator == 'u') { // Di chuyển ô trống lên trên
	        if (row > 0) { // Kiểm tra xem ô trống có thể di chuyển lên trên không
	            int tmp = currentState.getTile(row - 1, col);
	            result.updateTile(row - 1, col, 0);
	            result.updateTile(row, col, tmp);
	            result.setH(computeH2(result)); // Cập nhật giá trị heuristic
	            return result;
	        }
	    } else if (operator == 'd') { // Di chuyển ô trống xuống dưới
	        if (row < currentState.getRow() - 1) {
	            int tmp = currentState.getTile(row + 1, col);
	            result.updateTile(row + 1, col, 0);
	            result.updateTile(row, col, tmp);
	            result.setH(computeH2(result));
	            return result;
	        }
	    } else if (operator == 'l') { // Di chuyển ô trống sang trái
	        if (col > 0) {
	            int tmp = currentState.getTile(row, col - 1);
	            result.updateTile(row, col - 1, 0);
	            result.updateTile(row, col, tmp);
	            result.setH(computeH2(result));
	            return result;
	        }
	    } else if (operator == 'r') { // Di chuyển ô trống sang phải
	        if (col < currentState.getColumn() - 1) {
	            int tmp = currentState.getTile(row, col + 1);
	            result.updateTile(row, col + 1, 0);
	            result.updateTile(row, col, tmp);
	            result.setH(computeH2(result));
	            return result;
	        }
	    }
	    return null; // Trả về null nếu không thể di chuyển ô trống theo hướng đã chọn
	}


	public List<Node> getSuccessors(Node currentState) {
		ArrayList<Node> result = new ArrayList<Node>();

		for (char operator : operators) {
			Node tmp = moveWhiteTile(currentState, operator);
			if (tmp != null) {
				result.add(tmp);
			}
		}

		return result;
	}

	public Node getInitialState() {
		return initialState;
	}

	public Node getGoalState() {
		return goalState;
	}
}
