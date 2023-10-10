package lab1_task2;

import lab1_task2.Environment.LocationState;

public class TestSimpleReflexAgent {
	public static void main(String[] args) {
		Environment env = new Environment(Environment.LocationState.CLEAN, Environment.LocationState.DIRTY,
				LocationState.DIRTY, LocationState.DIRTY);
		Agent agent = new Agent(new AgentProgram());
		env.addAgent(agent, Environment.LOCATION_D);

		env.step(15);
	}
}
