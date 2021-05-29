package ua.smolii.puzzle.services;

import ua.smolii.puzzle.model.Board;
import ua.smolii.puzzle.model.Tile;

public class WinService {

	public boolean isWin(Board board) {
		Tile[][] positions = board.getPositions();

		Tile expected = Tile.getFirst();
		for (int row = 0; row < positions.length; row++) {
			for (int column = 0; column < positions[row].length; column++) { // todo second iteration through positions

				Tile actual = positions[row][column];

				boolean isLastRow = row == positions.length - 1;
				boolean isLastColumn = column == positions[row].length - 1;

				if (isLastRow && isLastColumn) {
					return actual == null; // todo or break?
				}

				if (expected.compareTo(actual) != 0) {
					return false;
				}
				expected = expected.getNext();
			}
		}

		return true;
	}
}
