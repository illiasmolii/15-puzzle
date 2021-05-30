package ua.smolii.puzzle.services.generator;

import java.util.List;

public class SolvabilityValidator {

	// https://stackoverflow.com/questions/34570344/check-if-15-puzzle-is-solvable
	public boolean isSolvable(List<Integer> puzzle) {
		int parity = 0;
		int gridWidth = (int) Math.sqrt(puzzle.size());
		int row = 0; // the current row we are on
		int blankRow = 0; // the row with the blank tile

		for (int i = 0; i < puzzle.size(); i++) {
			if (i % gridWidth == 0) { // advance to next row
				row++;
			}
			if (puzzle.get(i) == null) { // the blank tile
				blankRow = row; // save the row on which encountered
				continue;
			}
			for (int j = i + 1; j < puzzle.size(); j++) {
				if (puzzle.get(j) != null && puzzle.get(i) > puzzle.get(j)) {
					parity++;
				}
			}
		}

		if (gridWidth % 2 == 0) { // even grid
			if (blankRow % 2 == 0) { // blank on odd row; counting from bottom
				return parity % 2 == 0;
			} else { // blank on even row; counting from bottom
				return parity % 2 != 0;
			}
		} else { // odd grid
			return parity % 2 == 0;
		}
	}
}
