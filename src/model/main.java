package model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class main {
	public static void main(String args[]) throws IOException {
		findFile();
		State strategyA = parseFile("input.txt");
		State strategyB = strategyA.copy();
		
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
	
	public static void findFile() throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"));
		writer.write("TEST");
		writer.close();
	}
}


