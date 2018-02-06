package test;

import static org.junit.Assert.*;

import org.junit.Test;

import model.Jug;
import model.State;

public class test {
	
	@Test
	public void JugCreationTests() {
		Jug a = new Jug(0,0);
		assertEquals(a.getCapacity(),0);
		assertEquals(a.getState(),0);
		assertEquals(a.getName(),"0-gallon jug");
		a.fill();
		assertEquals(a.getState(),0);
		a = new Jug(4,2);
		assertEquals(a.getCapacity(),4);
		assertEquals(a.getState(),2);
		a.empty();
		assertEquals(a.getState(),0);
		a.fill();
		assertEquals(a.getState(),4);
	}
	
	@Test
	public void JugTransferTests() {
		Jug a = new Jug(4,0);
		Jug b = new Jug(3,0);
		a.fill();
		a.transferTo(b);
		assertEquals(a.getState(),1);
		assertEquals(b.getState(),3);
		b.empty();
		a.transferTo(b);
		assertEquals(a.getState(),0);
		assertEquals(b.getState(),1);
		a.fill();
		a.transferTo(b);
		assertEquals(a.getState(),2);
		assertEquals(b.getState(),3);
	}
	
	@Test
	public void StateTests() {
		State a = new State(4,3,0,0,2,-1);
		assertEquals(a.getState(), "(0,0)");
		a.applyRule(1);
		assertEquals(a.getState(), "(4,0)");
		assertFalse(a.isGoalState());
		a.applyRule(7);
		assertEquals(a.getState(),"(1,3)");
		a.applyRule(6);
		assertEquals(a.getState(),"(1,0)");
		a.applyRule(9);
		assertEquals(a.getState(),"(0,1)");
		a.applyRule(1);
		assertEquals(a.getState(),"(4,1)");
		a.applyRule(7);
		assertEquals(a.getState(),"(2,3)");
		assertTrue(a.isGoalState());
	}
}
