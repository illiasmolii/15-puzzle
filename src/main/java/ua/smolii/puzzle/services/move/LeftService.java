package ua.smolii.puzzle.services.move;

import ua.smolii.puzzle.model.Board;

public class LeftService extends MoveService {

	@Override
	public boolean isAllowedToMove(Board board) {
		return !board.isRightColumn();
	}

	@Override
	protected int getMovedTileRow(Board board) {
		return board.getEmptyPositionRow();
	}

	@Override
	protected int getMovedTileColumn(Board board) {
		return board.getEmptyPositionColumn() + 1;
	}
}
