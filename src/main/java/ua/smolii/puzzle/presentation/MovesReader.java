package ua.smolii.puzzle.presentation;

import java.io.BufferedReader;
import java.io.IOException;

import lombok.AllArgsConstructor;
import ua.smolii.puzzle.model.Direction;

@AllArgsConstructor
public class MovesReader {

	private final BufferedReader reader;

	public Direction read() throws IOException {
		String input = reader.readLine();
		switch (input) {
			case "W":
			case "w":
				return Direction.UP;
			case "S":
			case "s":
				return Direction.DOWN;
			case "A":
			case "a":
				return Direction.LEFT;
			case "D":
			case "d":
				return Direction.RIGHT;
			default:
				throw new InvalidDirectionException(input);
		}
	}
}
