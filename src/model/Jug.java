package model;

public class Jug {
	private int capacity;
	private int state;
	private String name;
	
	public Jug(int capacity, int state) {
		this.capacity = capacity;
		this.state = state;
		this.name = capacity + "-gallon jug";
	}
	
	public int getCapacity() {
		return capacity;
	}
	
	public int getState() {
		return state;
	}
	
	public String getName() {
		return name;
	}
	
	public void fill() {
		this.state = this.capacity;
	}
	
	public void empty() {
		this.state = 0;
	}
	
	public void transferTo(Jug fillMe) {
		if (this.state > (fillMe.getCapacity()-fillMe.getState())) {
			this.state -= (fillMe.getCapacity()-fillMe.getState());
			fillMe.fill();
		}
		else {
			fillMe.setState(fillMe.getState() + this.getState());
			this.empty();
		}
	}
	
	private void setState(int newState) {
		this.state = newState;
	}
}
