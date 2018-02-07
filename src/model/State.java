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
	
	public boolean canApply(int ruleNumber) {
		switch(ruleNumber) {
		case 1:
			if (jugA.getState() < jugA.getCapacity())
				return true;
			break;
		case 2:
			if (jugB.getState() < jugB.getCapacity())
				return true;
			break;
		case 3:
		case 4:
			// These are bad rules, ignore.
			return false;
		case 5:
			if (jugA.getState() > 0)
				return true;
			break;
		case 6: 
			if (jugB.getState() > 0)
				return true;
			break;
		case 7:
			if ((jugA.getState() + jugB.getState()) >= jugA.getCapacity() && jugB.getState() > 0 && jugA.getState() < jugA.getCapacity())
				return true;
			break;
		case 8:
			if ((jugA.getState() + jugB.getState()) >= jugB.getCapacity() && jugA.getState() > 0 && jugB.getState() < jugB.getCapacity())
				return true;
			break;
		case 9:
			if ((jugA.getState() + jugB.getState()) <= jugA.getCapacity() && jugB.getState() > 0 && jugA.getState() < jugA.getCapacity())
				return true;
			break;
		case 10:
			if ((jugA.getState() + jugB.getState()) <= jugB.getCapacity() && jugA.getState() > 0 && jugB.getState() < jugB.getCapacity())
				return true;
			break;
		default:
			break;
		}
		
		return false;
	}
	
	public String applyRule(int ruleNumber) {
		String ruleApplied = "";
		
		switch(ruleNumber) {
		case 1: 
			jugA.fill();
			ruleApplied += ">Fill the " + jugA.getName() + "\t\t\t\t\t\t";
			break;
		case 2:
			jugB.fill();
			ruleApplied += ">Fill the " + jugB.getName() + "\t\t\t\t\t\t";
			break;
		case 3:
		case 4:
			//System.out.println(">This is a bad rule.");
			break;
		case 5: 
			jugA.empty();
			ruleApplied += ">Empty the " + jugA.getName() + "\t\t\t\t\t\t";
			break;
		case 6: 
			jugB.empty();
			ruleApplied += ">Empty the " + jugB.getName() + "\t\t\t\t\t\t";
			break;
		case 7:
			jugB.transferTo(jugA);
			ruleApplied += ">Pour water from the " + jugB.getName() + " into the " + jugA.getName() + "\t\t";
			break;
		case 8:
			jugA.transferTo(jugB);
			ruleApplied += ">Pour water from the " + jugA.getName() + " into the " + jugB.getName() + "\t\t";
			break;
		case 9:
			jugB.transferTo(jugA);
			ruleApplied += ">Pour all water from the " + jugB.getName() + " into the " + jugA.getName() + "\t";
			break;
		case 10: 
			jugA.transferTo(jugB);
			ruleApplied += ">Pour all water from the " + jugA.getName() + " into the " + jugB.getName() + "\t";
			break;
		default:
			System.out.println(ruleNumber+" is not a valid rule # [1-10]");
			break;
		}
		this.expansions++;
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
	
	public int getNumExpansions() {
		return this.expansions;
	}
}
