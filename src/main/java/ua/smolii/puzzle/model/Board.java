package ua.smolii.puzzle.model;

import java.util.Map;

import org.javatuples.Pair;

import lombok.Getter;
import lombok.Setter;
import ua.smolii.puzzle.services.WinService;
import ua.smolii.puzzle.services.move.MoveService;

public class Board {

	@Getter
	private final Tile[][] positions;
	private final Map<Direction, MoveService> moveServices;
	private final WinService winService;
	private final int width;
	private final int height;

	@Getter
	@Setter
	private Pair<Integer, Integer> emptyPosition; // todo do we really need it?
	@Getter
	private GameState gameState;

	public Board(Tile[][] positions, Map<Direction, MoveService> moveServices, WinService winService) {
		this.positions = positions;
		this.moveServices = moveServices;
		this.winService = winService;
		this.emptyPosition = initEmptyPosition();
		this.width = positions.length;
		this.height = positions[0].length;
		this.gameState = GameState.CONTINUE;
	}

	private Pair<Integer, Integer> initEmptyPosition() {
		for (int row = 0; row < positions.length; row++) {
			for (int column = 0; column < positions[row].length; column++) { // todo first iteration through positions
				if (positions[row][column] == null) {
					return Pair.with(row, column);
				}
			}
		}

		throw new IllegalArgumentException("Board does not have an empty space");
	}

	public void move(Direction direction) {
		MoveService moveService = moveServices.get(direction);
		moveService.move(this);

		if (winService.isWin(this)) {
			gameState = GameState.WIN;
		}
	}

	// todo get rid of 4 methods below?
	public boolean isTopRow() {
		return emptyPosition.getValue0() == 0;
	}

	public boolean isBottomRow() {
		return emptyPosition.getValue0() == height - 1;
	}

	public boolean isLeftColumn() {
		return emptyPosition.getValue1() == 0;
	}

	public boolean isRightColumn() {
		return emptyPosition.getValue1() == width - 1;
	}

//	public <T> T iteratePositions(BiFunction<Integer, Integer, T> action) {
//		for (int row = 0; row < positions.length; row++) {
//			for (int column = 0; column < positions[row].length; column++) {
//				return action.apply(row, column);
//			}
//		}
//
//		throw new IllegalArgumentException("Board does not have an empty space");
//	}
}
