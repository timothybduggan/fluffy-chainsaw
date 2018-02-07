package model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class main {
	public static void main(String args[]) throws IOException {
		State strategyA = parseFile("input.txt");
		State strategyB = strategyA.copy();
		
		BufferedWriter out = new BufferedWriter(new FileWriter("output2.txt"));
		
		randomExpansion(strategyA, out);
		out.write("\n\n");
		randomExpansion(strategyB, out);
		
		out.close();
	}
	
	public static State parseFile(String fileName) {
		State state;
		File file = new File(fileName);
		Scanner input;
		try {
			input = new Scanner(file);
		} catch (FileNotFoundException e) {
			System.out.println("File not Found");
			e.printStackTrace();
			return null;
		}
		
		while(!input.hasNextInt()) input.next();
		int jugAcap = input.nextInt();
		while(!input.hasNextInt()) input.next();
		int jugBcap = input.nextInt();
		while(!input.hasNextInt()) input.next();
		int jugAinit = input.nextInt();
		while(!input.hasNextInt()) input.next();
		int jugBinit = input.nextInt();
		while(!input.hasNextInt()) input.next();
		int jugAgoal = input.nextInt();
		while(!input.hasNextInt()) input.next();
		int jugBgoal = input.nextInt();
		
		input.close();
		
		state = new State(jugAcap, jugBcap, jugAinit, jugBinit, jugAgoal, jugBgoal);
		
		return state;
	}

	public static void randomExpansion(State state, BufferedWriter out) throws IOException {
		Random gen = new Random();
		out.write(">Strategy A\n");
		int nextMove = -1;
		while (!state.isGoalState() && state.getNumExpansions() < 250) {
			nextMove = gen.nextInt(10)+1;
			while (!state.canApply(nextMove)) {
				//System.out.println("Picking a new number: " + nextMove);
				nextMove = gen.nextInt(10)+1;
			}
			String note = state.applyRule(nextMove) + "--- state: " + state.getState();
			System.out.println(note);
			out.write(note+"\n");
		}
	}

	public static void systematicExpansion(State state, BufferedWriter out) throws IOException{
		out.write(">Strategy B\n");
		
		// Figure out how to systematically expand this shit (don't return to any previous states)
		
	}
}


