import java.io.IOException;
import java.util.ArrayList;

public class SystematicStrategy extends Strategy {

	public SystematicStrategy(String fileName, State initialState, State goalState, Controller control)	throws IOException {
		super(fileName, true, initialState, goalState, control);
	}

	@Override
	public void solveProblem(Controller control) throws IOException {
		writeToFile("Strategy B\n");
		writeToFile(control.initialMessage(currentState));
		if (!currentState.isGoal(goalState)) {
			ArrayList<Integer> moves = backTrack(currentState, new ArrayList<State>());
			for (int i = 0; i < moves.size(); i++) {
				String text = control.applyRule(moves.get(i), currentState) + "\n";
				writeToFile(text);
			}
		}
	}

	private ArrayList<Integer> backTrack(State currentState, ArrayList<State> visitedStates) {
		// check if currentState is the goal state(i.e. not yet visited)
		if (currentState.isGoal(goalState))
			return null; // we will interpret this later
		
		// check if we are at a deadend
		for (State state : visitedStates) {
			if (currentState.isEqual(state)) {
				ArrayList<Integer> failed = new ArrayList<Integer>(); 
				failed.add(-1);	
				return failed;	// FailFlag == -1 at index 0 
			}
		}
		
		// make a list of applicable rules
		ArrayList<Integer> rules = applicableRules(currentState);
		int i = 0;
		
		while (!rules.isEmpty()) {
			int r = rules.get(0);
			rules.remove(0);
			State nextState = control.tryRule(r, currentState);
			visitedStates.add(0, currentState);
			ArrayList<Integer> path = backTrack(nextState, visitedStates); // visited states needs to be updated atm...
			
			if (path != null && path.get(0) == -1) { // if we have failed, try the next rule
				i++;
				visitedStates.remove(0);
				continue;
			}
			if (path == null) {
				path = new ArrayList<Integer>();
			}
			path.add(0, r); // insert this rule at the front of our path
			return path;
		}
		// if we get here we have run out of rules to apply, return fail
		ArrayList<Integer> failed = new ArrayList<Integer>();
		failed.add(-1);
		return failed;
	}
	
	private ArrayList<Integer> applicableRules(State state) {
		ArrayList<Integer> rules = new ArrayList<Integer>();
		for (int i = 10; i > 0; i--) {
			if (control.checkRule(i, state))
				rules.add(i);
		}
		
		return rules;
	}
}
