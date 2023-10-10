package lab1_task2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import lab1_task2.Environment.LocationState;

public class AgentProgram {

	// A|B
	// D|C
	Action randomAIAction(List<Action> action) {
		Random random = new Random();
		int randomAction = random.nextInt(action.size());
		Action nameAction = action.get(randomAction);
		return nameAction;
	}

	public Action execute(Percept p) {// location, status
		List<Action> listLocationA = new ArrayList<>();
		List<Action> listLocationB = new ArrayList<>();
		List<Action> listLocationC = new ArrayList<>();
		List<Action> listLocationD = new ArrayList<>();

		listLocationA.add(Environment.MOVE_RIGHT);
		listLocationA.add(Environment.MOVE_DOWN);

		listLocationB.add(Environment.MOVE_LEFT);
		listLocationB.add(Environment.MOVE_DOWN);

		listLocationC.add(Environment.MOVE_UP);
		listLocationC.add(Environment.MOVE_LEFT);

		listLocationD.add(Environment.MOVE_UP);
		listLocationD.add(Environment.MOVE_RIGHT);

		if (p.getLocationState() == LocationState.DIRTY) {
			return Environment.SUCK_DIRT;
		} else if (p.getAgentLocation() == Environment.LOCATION_A) {
			return randomAIAction(listLocationA);
		} else if (p.getAgentLocation() == Environment.LOCATION_B) {
			return randomAIAction(listLocationB);
		} else if (p.getAgentLocation() == Environment.LOCATION_C) {
			return randomAIAction(listLocationC);
		} else if (p.getAgentLocation() == Environment.LOCATION_D) {
			return randomAIAction(listLocationD);
		}
		return NoOpAction.NO_OP;
	}

}