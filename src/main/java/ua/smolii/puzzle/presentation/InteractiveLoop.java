package ua.smolii.puzzle.presentation;

import java.io.IOException;

import lombok.AllArgsConstructor;
import ua.smolii.puzzle.model.Board;
import ua.smolii.puzzle.model.Direction;
import ua.smolii.puzzle.model.GameState;

@AllArgsConstructor
public class InteractiveLoop {

	private final Board board;
	private final MovesReader movesReader;
	private final InterfacePrinter printer;

	public void startGame() throws IOException {
		printer.printStartInstructions();

		do {
			printer.print(board);
			try {
				Direction direction = movesReader.read();
				board.move(direction);
			}
			catch (IllegalArgumentException e) { // todo custom exception
				printer.printHelp(e.getMessage());
			}
		}
		while (board.getGameState() != GameState.WIN);

		printer.win();
	}
}
