import java.io.IOException;
import java.util.ArrayList;

public class SystematicStrategy extends Strategy {

	public SystematicStrategy(String fileName, State initialState, State goalState, Controller control)	throws IOException {
		super(fileName, false, initialState, goalState, control);
	}

	@Override
	public void solveProblem(Controller control) throws IOException {
		// TODO Auto-generated method stub
		ArrayList<Integer> ApplicableRules = new ArrayList<Integer>();
		// 
	}

	private ArrayList<Integer> backTrack(State currentState, ArrayList<State> visitedStates) {
		// check if currentState is the goal state(i.e. not yet visited)
		if (currentState.isGoal(goalState))
			return null; // we will interpret this later
		
		// check if we are at a deadend
		for (State state : visitedStates) {
			if (currentState.equals(state)) {
				ArrayList<Integer> failed = new ArrayList<Integer>(); 
				failed.add(-1);	
				return failed;	// FailFlag == -1 at index 0 
			}
		}
		
		// make a list of applicable rules
		ArrayList<Integer> rules = applicableRules(currentState);
		
		while (!rules.isEmpty()) {
			int r = rules.get(0);
			rules.remove(0);
			State nextState = control.tryRule(r, currentState);
			ArrayList<Integer> path = backTrack(nextState, visitedStates); // visited states needs to be updated atm...
			
			if (path != null && path.get(0) == -1) // if we have failed...
				continue;
			
			path.add(0, r); // insert this rule at the front of our path
			return path;
		}
		// if we get here we have run out of rules to apply, return fail
	}
	
	private ArrayList<Integer> applicableRules(State state) {
		ArrayList<Integer> rules = new ArrayList<Integer>()
		for (int i = 0; i < 11; i++) {
			if (control.checkRule(i, state))
				rules.add(i);
		}
		
		return rules;
	}
}
