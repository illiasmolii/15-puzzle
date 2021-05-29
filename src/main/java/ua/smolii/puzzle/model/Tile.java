package ua.smolii.puzzle.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tile implements Comparable<Tile> {

	private int value;

	@Override
	public int compareTo(Tile other) {
		return this.getValue() - other.getValue();
	}

	public Tile getNext() {
		return new Tile(this.getValue() + 1);
	}

	public static Tile getFirst() {
		return new Tile(1);
	}
}
