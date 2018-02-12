import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public abstract class Strategy {
	private BufferedWriter out;
	protected State currentState;
	protected State goalState;
	protected Controller control;
	
	public Strategy(String fileName, boolean append, State initialState, State goalState, Controller control) throws IOException {
		out = new BufferedWriter(new FileWriter(fileName, append));
		this.currentState = initialState.copy();
		this.goalState = goalState.copy();
		this.control = control;
	}
	
	public abstract void solveProblem(Controller control) throws IOException;
	
	public void writeToFile(String text) throws IOException {
		out.write(text);
		System.out.print(text);
	}
	
	public void end() throws IOException {
		out.close();
	}
}
