package ua.smolii.puzzle.presentation;

public class InvalidDirectionException extends RuntimeException {

	public InvalidDirectionException(String direction) {
		super("There is no direction: " + direction);
	}
}
