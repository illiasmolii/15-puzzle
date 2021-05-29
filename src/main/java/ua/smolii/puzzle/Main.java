package ua.smolii.puzzle;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import ua.smolii.puzzle.model.Board;
import ua.smolii.puzzle.model.Direction;
import ua.smolii.puzzle.presentation.InteractiveLoop;
import ua.smolii.puzzle.presentation.InterfacePrinter;
import ua.smolii.puzzle.presentation.MovesReader;
import ua.smolii.puzzle.services.WinService;
import ua.smolii.puzzle.services.generator.BoardGenerationService;
import ua.smolii.puzzle.services.generator.SolvabilityValidator;
import ua.smolii.puzzle.services.move.DownService;
import ua.smolii.puzzle.services.move.LeftService;
import ua.smolii.puzzle.services.move.MoveService;
import ua.smolii.puzzle.services.move.RightService;
import ua.smolii.puzzle.services.move.UpService;

public class Main {

	public static void main(String[] args) {
		// todo dependency injection
		Map<Direction, MoveService> moveServices = new HashMap<Direction, MoveService>() {{
			put(Direction.UP, new UpService());
			put(Direction.DOWN, new DownService());
			put(Direction.LEFT, new LeftService());
			put(Direction.RIGHT, new RightService());
		}};

		Board board = new Board(
				new BoardGenerationService(new SolvabilityValidator())
						.generatePositions(4), // todo ask user about size
				moveServices,
				new WinService()
		);

		try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
			MovesReader movesReader = new MovesReader(reader);
			InterfacePrinter printer = new InterfacePrinter();

			new InteractiveLoop(board, movesReader, printer).startGame();
		} catch (IOException e) {
			System.err.print("An error occurred: " + e.getMessage());
		}
	}
}
