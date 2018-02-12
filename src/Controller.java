
public class Controller {
//	private State currentState;
	private int jugAcapacity;
	private int jugBcapacity;
//	private State goalState;	// 
//	private Strategy strategy;	// Random or Systematic (will affect the 'findPath' function)
	private String jugAname;
	private String jugBname;
	
	public Controller(State initialState, int jugAcapacity, int jugBcapacity, State goalState) {
//		this.currentState = initialState.copy();
		this.jugAcapacity = jugAcapacity;
		this.jugBcapacity = jugBcapacity;
//		this.goalState = goalState.copy();
//		this.strategy = strategy;
		this.jugAname = jugAcapacity+"-gallon jug";
		this.jugBname = jugBcapacity+"-gallon jug";
	}
	
	public boolean checkRule(int ruleNumber, State state) {
		if (state == null) return false;
		
		switch(ruleNumber) {
		case 1:
			if (state.getJugA() < jugAcapacity) {
				return true;
			}
			return false;
		case 2:
			if (state.getJugB() < jugBcapacity) {
				return true;
			}
			return false;
		case 3:
		case 4:
			return false;
		case 5: 
			if (state.getJugA() > 0) {
				return true;
			}
			return false;
		case 6:
			if (state.getJugB() > 0) {
				return true;
			}
			return false;
		case 7:
			if (state.getJugSum() >= jugAcapacity && state.getJugB() > 0) { // pour from B to A until A full
				return true;
			}
			return false;
		case 8:
			if (state.getJugSum() >= jugBcapacity && state.getJugA() > 0) { // pour from A to B until B full
				return true;
			}
			return false;
		case 9:
			if (state.getJugSum() <= jugAcapacity && state.getJugB() > 0) { // pour from B to A until B empty
				return true;
			}
			return false;
		case 10:
			if (state.getJugSum() <= jugBcapacity && state.getJugA() > 0) { // pour from A to B until A empty
				return true;
			}
			return false;
		default:
			return false;
		}
	}
	
	public String applyRule(int ruleNumber, State state) {
		if (!checkRule(ruleNumber, state)) return null; // this should be checked by the caller before rule is applied, but we will double check
		String writeBack = "";
		
		state = tryRule(ruleNumber, state);
		
		switch(ruleNumber) {
		case 1: 
			writeBack += "Fill the "+jugAname+"\t\t\t\t";
			break;
		case 2:
			writeBack += "Fill the "+jugBname+"\t\t\t\t";
			break;
		case 5:
			writeBack += "Empty the "+jugAname+"\t\t\t\t";
			break;
		case 6:
			writeBack += "Empty the "+jugBname+"\t\t\t\t";
			break;
		case 7:
		case 9:
			writeBack += "Pour water from the "+jugBname+" into the "+jugAname+"\t";
			break;
		case 8:
		case 10:
			writeBack += "Pour water from the "+jugAname+" into the "+jugBname+"\t";
			break;
		default:
			throw new IllegalArgumentException("Should be Unreachable");
		}
		
		writeBack += "-- state:"+state.toString();
		
		return writeBack;
	}
	
	public State tryRule(int ruleNumber, State state) { // returns what the output state of applying a given rule would be
		State nextState = state.copy();
		
		switch(ruleNumber) {
		case 1:
			nextState.updateState(jugAcapacity, nextState.getJugB());
			break;
		case 2:
			nextState.updateState(nextState.getJugA(), jugBcapacity);
			break;
		case 5:
			nextState.updateState(0, nextState.getJugB());
			break;
		case 6:
			nextState.updateState(nextState.getJugA(), 0);
			break;
		case 7:
			nextState.updateState(jugAcapacity, nextState.getJugSum()-jugAcapacity);
			break;
		case 8:
			nextState.updateState(nextState.getJugSum()-jugBcapacity, jugBcapacity);
			break;
		case 9:
			nextState.updateState(nextState.getJugSum(), 0);
			break;
		case 10:
			nextState.updateState(0, nextState.getJugSum());
			break;
		default:
			break;
		}
		
		return nextState;
	}
}



