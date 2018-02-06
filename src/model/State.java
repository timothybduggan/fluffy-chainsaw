package model;

public class State {
	private Jug jugA;
	private Jug jugB;
	private boolean done;
	private int jugAgoal;
	private int jugBgoal;
	private int expansions;
	
	/* Parameters:
	 * 	capacity of jug A, capacity of jug B
	 *  initial state of jug A, initial state of jug B
	 *  goal state of jug A, goal state of jug B
	 */
	public State(int jugAcapacity, int jugBcapacity, int jugAinitial, int jugBinitial, int jugAgoal, int jugBgoal) {
		jugA = new Jug(jugAcapacity, jugAinitial);
		jugB = new Jug(jugBcapacity, jugBinitial);
		this.jugAgoal = jugAgoal;
		this.jugBgoal = jugBgoal;
		this.expansions = 0;
	}
	
	public String getState() {
		return "("+jugA.getState()+","+jugB.getState()+")";
	}
	
	public String applyRule(int ruleNumber) {
		String ruleApplied = "";
		
		switch(ruleNumber) {
		case 1: 
			jugA.fill();
			ruleApplied = "Fill the " + jugA.getName();
			break;
		case 2:
			jugB.fill();
			ruleApplied = "Fill the " + jugB.getName();
			break;
		case 3:
			System.out.println("This is a bad rule.");
			break;
		case 4:
			System.out.println("This is a bad rule.");
			break;
		case 5: 
			jugA.empty();
			ruleApplied = "Empty the " + jugA.getName();
			break;
		case 6: 
			jugB.empty();
			ruleApplied = "Empty the " + jugB.getName();
			break;
		case 7:
			jugA.transferTo(jugB);
			ruleApplied = "Pour water from the " + jugA.getName() + " into the " + jugB.getName();
			break;
		case 8:
			jugB.transferTo(jugA);
			ruleApplied = "Pour water from the " + jugB.getName() + " into the " + jugA.getName();
			break;
		case 9:
			jugA.transferTo(jugB);
			ruleApplied = "Pour water from the " + jugA.getName() + " into the " + jugB.getName();
			break;
		case 10: 
			jugB.transferTo(jugA);
			ruleApplied = "Pour water from the " + jugB.getName() + " into the " + jugA.getName();
			break;
		default:
			System.out.println(ruleNumber+" is not a valid rule # [1-10]");
			break;
		}
		
		return ruleApplied;
	}

	public boolean isGoalState() {
		if (jugAgoal != -1 && jugA.getState() != jugAgoal)
			done = false;
		else if (jugBgoal != -1 && jugB.getState() != jugBgoal)
			done = false;
		else 
			done = true;
		return done;
	}
	
	public State copy() {
		return new State(jugA.getCapacity(), jugB.getCapacity(), jugA.getState(), jugB.getState(), this.jugAgoal, this.jugBgoal);
	}
}
