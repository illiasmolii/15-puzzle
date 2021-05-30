package ua.smolii.puzzle.model;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import org.javatuples.Pair;

import lombok.Getter;
import lombok.Setter;
import ua.smolii.puzzle.services.move.MoveService;

public class Board {

	@Getter // for testing purposes
	private final Tile[][] positions;
	private final List<Tile> winPositionsFlattened;
	private final int width;
	private final int height;

	private final Map<Direction, MoveService> moveServices;

	@Getter
	@Setter
	private Pair<Integer, Integer> emptyPosition;
	@Getter
	private GameState gameState;

	public Board(Tile[][] positions, Map<Direction, MoveService> moveServices) {
		this.positions = positions;
		this.winPositionsFlattened = initWinPositionsFlattened();
		this.width = positions.length;
		this.height = positions[0].length;
		this.moveServices = moveServices;
		this.emptyPosition = initEmptyPosition();
		this.gameState = GameState.CONTINUE;
	}

	private List<Tile> initWinPositionsFlattened() {
		List<Tile> winPositions = Arrays.stream(positions)
				.flatMap(Arrays::stream)
				.filter(Objects::nonNull)
				.sorted()
				.collect(Collectors.toList());
		winPositions.add(null);

		return winPositions;
	}

	private Pair<Integer, Integer> initEmptyPosition() {
		for (int row = 0; row < positions.length; row++) {
			for (int column = 0; column < positions[row].length; column++) {
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

		if (isWin()) {
			gameState = GameState.WIN;
		}
	}

	private boolean isWin() {
		return winPositionsFlattened.equals(getPositionsFlattened());
	}

	public Tile getTile(int row, int column) {
		return positions[row][column];
	}

	public void setTile(Tile tile, int row, int column) {
		positions[row][column] = tile;
	}

	public int getEmptyPositionColumn() {
		return emptyPosition.getValue1();
	}

	public int getEmptyPositionRow() {
		return emptyPosition.getValue0();
	}

	public boolean isTopRow() {
		return getEmptyPositionRow() == 0;
	}

	public boolean isBottomRow() {
		return getEmptyPositionRow() == height - 1;
	}

	public boolean isLeftColumn() {
		return getEmptyPositionColumn() == 0;
	}

	public boolean isRightColumn() {
		return getEmptyPositionColumn() == width - 1;
	}

	public List<Tile> getPositionsFlattened() {
		return Arrays.stream(positions)
				.flatMap(Arrays::stream)
				.collect(Collectors.toList());
	}

	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		for (int row = 0; row < positions.length; row++) {
			for (int column = 0; column < positions[row].length; column++) {
				Tile tile = positions[row][column];
				if (tile != null) {
					stringBuilder.append("[")
							.append(String.format("%02d", tile.getValue()))
							.append("] ");
				} else {
					stringBuilder.append(" XX  ");
				}
			}
			stringBuilder.append("\n");
		}
		return stringBuilder.toString();
	}
}
