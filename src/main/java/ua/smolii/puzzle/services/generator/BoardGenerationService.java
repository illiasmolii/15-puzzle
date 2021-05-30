package ua.smolii.puzzle.services.generator;

import static java.util.stream.Collectors.toList;

import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

import lombok.AllArgsConstructor;
import ua.smolii.puzzle.model.Tile;

@AllArgsConstructor
public class BoardGenerationService {

	private final SolvabilityValidator validator;

	public Tile[][] generatePositions(int size) {
		List<Integer> values = IntStream.range(
				1,
				(int) Math.pow(size, 2)
		).boxed().collect(toList());

		values.add(null);

		int attempts = 0;
		do {
			Collections.shuffle(values);
			attempts++;
		} while (!validator.isSolvable(values));
		System.out.println("============= DEBUG. " + attempts + " attempts to generate solvable puzzle");

		Tile[][] result = new Tile[size][size];

		int counter = 0;
		for (int row = 0; row < size; row++) {
			for (int column = 0; column < size; column++) {
				Integer value = values.get(counter);
				result[row][column] =
						value != null
								? new Tile(value)
								: null;
				counter++;
			}
		}

		return result;
	}
}
