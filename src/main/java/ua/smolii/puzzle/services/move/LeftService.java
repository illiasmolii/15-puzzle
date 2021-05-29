package ua.smolii.puzzle.services.move;

import ua.smolii.puzzle.model.Board;

public class LeftService extends MoveService {

	@Override
	public boolean isAllowedToMove(Board board) {
		return !board.isRightColumn();
	}

	@Override
	protected int getMovedTileY(Board board) {
		return getEmptyPositionY(board);
	}

	@Override
	protected int getMovedTileX(Board board) {
		return getEmptyPositionX(board) + 1;
	}
}
