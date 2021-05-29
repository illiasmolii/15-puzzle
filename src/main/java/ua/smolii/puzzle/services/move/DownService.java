package ua.smolii.puzzle.services.move;

import ua.smolii.puzzle.model.Board;

public class DownService extends MoveService {

	@Override
	public boolean isAllowedToMove(Board board) {
		return !board.isTopRow();
	}

	@Override
	protected int getMovedTileY(Board board) {
		return getEmptyPositionY(board) - 1;
	}

	@Override
	protected int getMovedTileX(Board board) {
		return getEmptyPositionX(board);
	}
}
