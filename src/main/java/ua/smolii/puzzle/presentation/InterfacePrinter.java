package ua.smolii.puzzle.presentation;

import ua.smolii.puzzle.model.Board;

// todo inject System.out to make it testable
public class InterfacePrinter {

	public void printStartInstructions() {
		System.out.println("Welcome to our exciting puzzle");
		System.out.println("For rules and description see https://en.wikipedia.org/wiki/15_puzzle\n");
		printHelp("");
	}

	public void printHelp(String... errors) {
		System.out.println(errors[0]);
		System.out.println("Commands (case insensitive):");
		System.out.println("W: move Tile up to free place");
		System.out.println("S: move Tile down to free place");
		System.out.println("A: move Tile left to free place");
		System.out.println("D: move Tile right to free place");
		System.out.println("\nEnjoy your game!!!\n");
	}

	public void print(Board board) {
		System.out.println(board);
	}

	public void win() {
		System.out.println("YOU WIN!!!");
		System.out.println("CONGRATULATIONS!!!");
	}
}
