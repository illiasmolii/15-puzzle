package ua.smolii.puzzle.services.move;

import org.javatuples.Pair;

import ua.smolii.puzzle.model.Board;
import ua.smolii.puzzle.model.Tile;

public abstract class MoveService {

	public void move(Board board) {
		if (!isAllowedToMove(board)) {
			return;
		}

		int emptyRow = board.getEmptyPositionRow();
		int emptyColumn = board.getEmptyPositionColumn();

		int movedTileRow = getMovedTileRow(board);
		int movedTileColumn = getMovedTileColumn(board);

		Tile movedTile = board.getTile(movedTileRow, movedTileColumn);

		board.setTile(movedTile, emptyRow, emptyColumn);
		board.setTile(null, movedTileRow, movedTileColumn);

		board.setEmptyPosition(Pair.with(movedTileRow, movedTileColumn));
	}

	protected abstract boolean isAllowedToMove(Board board);

	protected abstract int getMovedTileRow(Board board);

	protected abstract int getMovedTileColumn(Board board);
}
