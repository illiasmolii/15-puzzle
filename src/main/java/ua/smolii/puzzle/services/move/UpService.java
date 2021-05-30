package ua.smolii.puzzle.services.move;

import ua.smolii.puzzle.model.Board;

public class UpService extends MoveService {

	@Override
	public boolean isAllowedToMove(Board board) {
		return !board.isBottomRow();
	}

	@Override
	protected int getMovedTileRow(Board board) {
		return board.getEmptyPositionRow() + 1;
	}

	@Override
	protected int getMovedTileColumn(Board board) {
		return board.getEmptyPositionColumn();
	}
}
