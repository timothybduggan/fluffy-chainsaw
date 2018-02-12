
public class State {
	private int jugA;
	private int jugB;
	
	public State(int jugA, int jugB) {
		this.jugA = jugA;
		this.jugB = jugB;
	}
	
	public State() {
		this(0,0);
	}
	
	public int getJugA() {
		return this.jugA;
	}
	
	public int getJugB() {
		return this.jugB;
	}
	
	public int getJugSum() {
		return this.jugA + this.jugB;
	}
	
	public void updateState(int jugA, int jugB) {
		this.jugA = jugA;
		this.jugB = jugB;
	}
	
	public boolean isGoal(State goal) {
		if (goal.getJugA() != -1 && this.getJugA() != goal.getJugA()) 
			return false;
		if (goal.getJugB() != -1 && this.getJugB() != goal.getJugB())
			return false;
		return true;
	}
	
	public boolean isEqual(State compareTo) {
		if (compareTo.getJugA() != this.getJugA())
			return false;
		if (compareTo.getJugB() != this.getJugB())
			return false;
		return true;
	}
	
	public State copy() {
		return new State(this.jugA, this.jugB);
	}
	
	public String toString() {
		return "("+jugA+","+jugB+")";
	}
}
