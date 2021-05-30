package ua.smolii.puzzle.services.move;

import ua.smolii.puzzle.model.Board;

public class DownService extends MoveService {

	@Override
	public boolean isAllowedToMove(Board board) {
		return !board.isTopRow();
	}

	@Override
	protected int getMovedTileRow(Board board) {
		return board.getEmptyPositionRow() - 1;
	}

	@Override
	protected int getMovedTileColumn(Board board) {
		return board.getEmptyPositionColumn();
	}
}
