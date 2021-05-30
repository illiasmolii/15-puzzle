package ua.smolii.puzzle;

import java.util.HashMap;
import java.util.Map;

import ua.smolii.puzzle.model.Board;
import ua.smolii.puzzle.model.Direction;
import ua.smolii.puzzle.presentation.InteractiveLoopProvider;
import ua.smolii.puzzle.services.generator.BoardGenerationService;
import ua.smolii.puzzle.services.generator.SolvabilityValidator;
import ua.smolii.puzzle.services.move.DownService;
import ua.smolii.puzzle.services.move.LeftService;
import ua.smolii.puzzle.services.move.MoveService;
import ua.smolii.puzzle.services.move.RightService;
import ua.smolii.puzzle.services.move.UpService;

public class Main {

	private static final int BOARD_SIZE = 4;

	public static void main(String[] args) {
		Map<Direction, MoveService> moveServices = new HashMap<Direction, MoveService>() {{
			put(Direction.UP, new UpService());
			put(Direction.DOWN, new DownService());
			put(Direction.LEFT, new LeftService());
			put(Direction.RIGHT, new RightService());
		}};

		Board board = new Board(
				new BoardGenerationService(new SolvabilityValidator())
						.generatePositions(BOARD_SIZE),
				moveServices
		);

		new InteractiveLoopProvider().start(board);
	}
}
