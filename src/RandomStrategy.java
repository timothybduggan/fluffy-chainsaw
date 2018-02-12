import java.io.IOException;
import java.util.Random;

public class RandomStrategy extends Strategy {
	private int numSteps;
	
	public RandomStrategy(String fileName, State initialState, State goalState, Controller control) throws IOException {
		super(fileName, false, initialState, goalState, control);
		this.numSteps = 0;
	}

	public void solveProblem(Controller control) throws IOException {
		Random generator = new Random();
		writeToFile(">Strategy A\n");
		while (numSteps < 250 && !currentState.isGoal(goalState)) {
			// while we have taken < 250 steps, and we haven't reached goal state...
			int rule = 0;
			while (!control.checkRule(rule, currentState)) { 
				// while the rule we have chosen is not valid...
				rule = generator.nextInt(10)+1; // pick a rule from 1-10
			}
			// when we get here, our rule is valid..
			String text = ">" + control.applyRule(rule, currentState) + "\n";
			
			writeToFile(text);
		}
	}

}
