package lab1_task2;

public class Environment {
	public static final Action MOVE_LEFT = new DynamicAction("LEFT");
	public static final Action MOVE_RIGHT = new DynamicAction("RIGHT");
	public static final Action MOVE_UP = new DynamicAction("UP");
	public static final Action MOVE_DOWN = new DynamicAction("DOWN");
	public static final Action SUCK_DIRT = new DynamicAction("SUCK");
	public static final String LOCATION_A = "A";
	public static final String LOCATION_B = "B";
	public static final String LOCATION_C = "C";
	public static final String LOCATION_D = "D";

	public enum LocationState {
		CLEAN, DIRTY
	}

	private EnvironmentState envState;
	private boolean isDone = false;// all squares are CLEAN
	private Agent agent = null;
	int mark = 0;

	public Environment(LocationState locAState, LocationState locBState, LocationState locCState,
			LocationState locDState) {
		envState = new EnvironmentState(locAState, locBState, locCState, locDState);
	}

	// add an agent into the enviroment
	public void addAgent(Agent agent, String location) {
		// TODO
		this.agent = agent;
		envState.setAgentLocation(location);
	}

	public EnvironmentState getCurrentState() {
		return this.envState;
	}

	// A|B
	// D|C
	// Update enviroment state when agent do an action

	public EnvironmentState executeAction(Action action) {
		// TODO
		if (action == Environment.SUCK_DIRT) {
			envState.setLocationState(envState.getAgentLocation(), LocationState.CLEAN);
		} else if (action == Environment.MOVE_LEFT) {
			envState.setAgentLocation(leftAction(envState.getAgentLocation()));
		} else if (action == Environment.MOVE_RIGHT) {
			envState.setAgentLocation(rightAction(envState.getAgentLocation()));
		} else if (action == Environment.MOVE_UP) {
			envState.setAgentLocation(upAction(envState.getAgentLocation()));
		} else if (action == Environment.MOVE_DOWN) {
			envState.setAgentLocation(downAction(envState.getAgentLocation()));
		}
		return envState;
	}

	private String leftAction(String currentLoc) {
		if (currentLoc.equals(LOCATION_B)) {
			return LOCATION_A;
		} else if (currentLoc.equals(LOCATION_C)) {
			return LOCATION_D;
		}
		return currentLoc;
	}

	private String rightAction(String currentLoc) {
		if (currentLoc.equals(LOCATION_A)) {
			return LOCATION_B;
		} else if (currentLoc.equals(LOCATION_D)) {
			return LOCATION_C;
		}
		return currentLoc;
	}

	private String upAction(String currentLoc) {
		if (currentLoc.equals(LOCATION_D)) {
			return LOCATION_A;
		} else if (currentLoc.equals(LOCATION_C)) {
			return LOCATION_B;
		}
		return currentLoc;
	}

	private String downAction(String currentLoc) {
		if (currentLoc.equals(LOCATION_A)) {
			return LOCATION_D;
		} else if (currentLoc.equals(LOCATION_B)) {
			return LOCATION_C;
		}
		return currentLoc;
	}

	// get percept<AgentLocation, LocationState> at the current location where agent
	// is in.
	public Percept getPerceptSeenBy() {
		// TODO
		return new Percept(envState.getAgentLocation(), envState.getLocationState(envState.getAgentLocation()));
	}

	public void step() {
		envState.display();
		String agentLocation = this.envState.getAgentLocation();
		Action anAction = agent.execute(getPerceptSeenBy());
		EnvironmentState es = executeAction(anAction);

		System.out.println("Agent Loc.: " + agentLocation + "\tAction: " + anAction + "\nPoint: " + point());

		if ((es.getLocationState(LOCATION_A) == LocationState.CLEAN)
				&& (es.getLocationState(LOCATION_B) == LocationState.CLEAN)
				&& (es.getLocationState(LOCATION_C) == LocationState.CLEAN)
				&& (es.getLocationState(LOCATION_D) == LocationState.CLEAN))
			isDone = true;// if both squares are clean, then agent do not need to do any action
		es.display();
	}

	public void step(int n) {
		for (int i = 0; i < n; i++) {
			step();
			System.out.println("-------------------------");
		}
	}

	public void stepUntilDone() {
		int i = 0;

		while (!isDone) {
			System.out.println("step: " + i++);
			step();
		}
	}

	int point() {
		if (agent.execute(getPerceptSeenBy()) == Environment.SUCK_DIRT) {
			mark += 500;
		}
		if (agent.execute(getPerceptSeenBy()) == NoOpAction.NO_OP) {
			mark -= 100;
		} else {
			mark -= 10;
		}
		return mark;
	}

}
