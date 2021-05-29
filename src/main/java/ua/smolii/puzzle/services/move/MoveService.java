package ua.smolii.puzzle.services.move;

import org.javatuples.Pair;

import ua.smolii.puzzle.model.Board;
import ua.smolii.puzzle.model.Tile;

public abstract class MoveService {

	public void move(Board board) {
		if (!isAllowedToMove(board)) {
			return;
		}

		int y = getEmptyPositionY(board);
		int x = getEmptyPositionX(board);

		int movedTileY = getMovedTileY(board);
		int movedTileX = getMovedTileX(board);

		Tile[][] positions = board.getPositions();
		Tile movedTile = positions[movedTileY][movedTileX];

		positions[y][x] = movedTile;

		positions[movedTileY][movedTileX] = null;
		board.setEmptyPosition(Pair.with(movedTileY, movedTileX));
	}

	protected int getEmptyPositionX(Board board) {
		return board.getEmptyPosition().getValue1();
	}

	protected int getEmptyPositionY(Board board) {
		return board.getEmptyPosition().getValue0();
	}

	protected abstract boolean isAllowedToMove(Board board);

	protected abstract int getMovedTileY(Board board);

	protected abstract int getMovedTileX(Board board);
}
