import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class JugProblem {
	
	static State initial = new State();
	static State goal = new State();
	static int jugAcapacity;
	static int jugBcapacity;
	
	public static void main(String[] args) throws IOException {
		parseFile(args[0]);
		Controller control = new Controller(initial, jugAcapacity, jugBcapacity, goal);
		
		Strategy strategyA = new RandomStrategy("output.txt", initial, goal, control);
		strategyA.solveProblem(control);
		strategyA.end();
		
		Strategy strategyB = new SystematicStrategy("output.txt", initial, goal, control);
		strategyB.solveProblem(control);
		strategyB.end();
	}
	
	public static void parseFile(String fileName) {
		File file = new File(fileName);
		Scanner input;
		try {
			input = new Scanner(file);
		} catch (FileNotFoundException e) { 
			System.out.println("File not Found");
			e.printStackTrace();
			return;
		}
		
		while (!input.hasNextInt()) input.next(); // scroll to next int
		jugAcapacity = input.nextInt();
		while (!input.hasNextInt()) input.next(); // scroll to next int
		jugBcapacity = input.nextInt();
		while (!input.hasNextInt()) input.next(); // scroll to next int
		int jugAinitial = input.nextInt();
		while (!input.hasNextInt()) input.next(); // scroll to next int
		int jugBinitial = input.nextInt();
		while (!input.hasNextInt()) input.next(); // scroll to next int
		int jugAgoal = input.nextInt();
		while (!input.hasNextInt()) input.next(); // scroll to next int
		int jugBgoal = input.nextInt();
		
		input.close();
		
		initial.updateState(jugAinitial, jugBinitial);
		goal.updateState(jugAgoal, jugBgoal);
	}
}
