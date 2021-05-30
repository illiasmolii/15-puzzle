package ua.smolii.puzzle.presentation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import ua.smolii.puzzle.model.Board;

public class InteractiveLoopProvider {

	public void start(Board board) {
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
			new InteractiveLoop(
					new MovesReader(reader),
					new InterfacePrinter(),
					board
			).startGame();
		} catch (IOException e) {
			System.err.print("An error occurred: " + e.getMessage());
		}
	}
}
