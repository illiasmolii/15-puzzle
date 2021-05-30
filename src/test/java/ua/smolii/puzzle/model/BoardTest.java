package ua.smolii.puzzle.model;

import static org.assertj.core.api.Assertions.assertThat;
import static ua.smolii.puzzle.model.Direction.DOWN;
import static ua.smolii.puzzle.model.Direction.LEFT;
import static ua.smolii.puzzle.model.Direction.RIGHT;
import static ua.smolii.puzzle.model.Direction.UP;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import ua.smolii.puzzle.services.move.DownService;
import ua.smolii.puzzle.services.move.LeftService;
import ua.smolii.puzzle.services.move.MoveService;
import ua.smolii.puzzle.services.move.RightService;
import ua.smolii.puzzle.services.move.UpService;

@ExtendWith(MockitoExtension.class)
class BoardTest {

	private static final Map<Direction, MoveService> moveServices = new HashMap<Direction, MoveService>() {{
		put(Direction.UP, new UpService());
		put(Direction.DOWN, new DownService());
		put(Direction.LEFT, new LeftService());
		put(Direction.RIGHT, new RightService());
	}};

	@Test
	void tile_is_moved_up() {
		// given
		Board board = new Board(
				new Tile[][] {
						{new Tile(), new Tile(), new Tile(), new Tile()},
						{new Tile(), new Tile(), new Tile(), new Tile()},
						{new Tile(), new Tile(), 	   null, new Tile()},
						{new Tile(), new Tile(), new Tile(), new Tile()}
				},
				moveServices
		);

		// when
		board.move(UP);

		// then
		assertThat(board.getPositions()).isEqualTo(
				new Tile[][] {
						{new Tile(), new Tile(), new Tile(), new Tile()},
						{new Tile(), new Tile(), new Tile(), new Tile()},
						{new Tile(), new Tile(), new Tile(), new Tile()},
						{new Tile(), new Tile(), 	   null, new Tile()}
				}
		);
		assertThat(board.getEmptyPosition().getValue0()).isEqualTo(3);
		assertThat(board.getEmptyPosition().getValue1()).isEqualTo(2);
	}

	@Test
	void tile_is_moved_down() {
		// given
		Board board = new Board(
				new Tile[][] {
						{new Tile(), new Tile(), new Tile(), new Tile()},
						{new Tile(), new Tile(), new Tile(), new Tile()},
						{new Tile(), new Tile(), 	   null, new Tile()},
						{new Tile(), new Tile(), new Tile(), new Tile()}
				},
				moveServices
		);

		// when
		board.move(DOWN);

		// then
		assertThat(board.getPositions()).isEqualTo(
				new Tile[][] {
						{new Tile(), new Tile(), new Tile(), new Tile()},
						{new Tile(), new Tile(), 	   null, new Tile()},
						{new Tile(), new Tile(), new Tile(), new Tile()},
						{new Tile(), new Tile(), new Tile(), new Tile()}
				}
		);
		assertThat(board.getEmptyPosition().getValue0()).isEqualTo(1);
		assertThat(board.getEmptyPosition().getValue1()).isEqualTo(2);
	}

	@Test
	void tile_is_moved_left() {
		// given
		Board board = new Board(
				new Tile[][] {
						{new Tile(), new Tile(), new Tile(), new Tile()},
						{new Tile(), new Tile(), new Tile(), new Tile()},
						{new Tile(), new Tile(), 	   null, new Tile()},
						{new Tile(), new Tile(), new Tile(), new Tile()}
				},
				moveServices
		);

		// when
		board.move(LEFT);

		// then
		assertThat(board.getPositions()).isEqualTo(
				new Tile[][] {
						{new Tile(), new Tile(), new Tile(), new Tile()},
						{new Tile(), new Tile(), new Tile(), new Tile()},
						{new Tile(), new Tile(), new Tile(), 	   null},
						{new Tile(), new Tile(), new Tile(), new Tile()}
				}
		);
		assertThat(board.getEmptyPosition().getValue0()).isEqualTo(2);
		assertThat(board.getEmptyPosition().getValue1()).isEqualTo(3);
	}

	@Test
	void tile_is_moved_right() {
		// given
		Board board = new Board(
				new Tile[][] {
						{new Tile(), new Tile(), new Tile(), new Tile()},
						{new Tile(), new Tile(), new Tile(), new Tile()},
						{new Tile(), new Tile(), 	   null, new Tile()},
						{new Tile(), new Tile(), new Tile(), new Tile()}
				},
				moveServices
		);

		// when
		board.move(RIGHT);

		// then
		assertThat(board.getPositions()).isEqualTo(
				new Tile[][] {
						{new Tile(), new Tile(), new Tile(), new Tile()},
						{new Tile(), new Tile(), new Tile(), new Tile()},
						{new Tile(), 	   null, new Tile(), new Tile()},
						{new Tile(), new Tile(), new Tile(), new Tile()}
				}
		);
		assertThat(board.getEmptyPosition().getValue0()).isEqualTo(2);
		assertThat(board.getEmptyPosition().getValue1()).isEqualTo(1);
	}

	@Test
	void move_down_is_not_allowed_at_the_top_row() {
		// given
		Tile[][] initialPositions = {
				{new Tile(), new Tile(), 	   null, new Tile()},
				{new Tile(), new Tile(), new Tile(), new Tile()},
				{new Tile(), new Tile(), new Tile(), new Tile()},
				{new Tile(), new Tile(), new Tile(), new Tile()}
		};
		Board board = new Board(initialPositions, moveServices);

		// when - then
		board.move(DOWN);

		assertThat(board.getPositions()).isEqualTo(initialPositions);
		assertThat(board.getEmptyPosition().getValue0()).isEqualTo(0);
		assertThat(board.getEmptyPosition().getValue1()).isEqualTo(2);
	}

	@Test
	void move_up_is_not_allowed_at_the_bottom_row() {
		// given
		Tile[][] initialPositions = {
				{new Tile(), new Tile(), new Tile(), new Tile()},
				{new Tile(), new Tile(), new Tile(), new Tile()},
				{new Tile(), new Tile(), new Tile(), new Tile()},
				{new Tile(), 	   null, new Tile(), new Tile()}
		};
		Board board = new Board(initialPositions, moveServices);

		// when
		board.move(UP);

		// then
		assertThat(board.getPositions()).isEqualTo(initialPositions);
		assertThat(board.getEmptyPosition().getValue0()).isEqualTo(3);
		assertThat(board.getEmptyPosition().getValue1()).isEqualTo(1);
	}

	@Test
	void move_left_is_not_allowed_at_the_right_column() {
		// given
		Tile[][] initialPositions = {
				{new Tile(), new Tile(), new Tile(), new Tile()},
				{new Tile(), new Tile(), new Tile(), new Tile()},
				{new Tile(), new Tile(), new Tile(), new Tile()},
				{new Tile(), new Tile(), new Tile(), 	   null}
		};
		Board board = new Board(initialPositions, moveServices);

		// when
		board.move(LEFT);

		// then
		assertThat(board.getPositions()).isEqualTo(initialPositions);
		assertThat(board.getEmptyPosition().getValue0()).isEqualTo(3);
		assertThat(board.getEmptyPosition().getValue1()).isEqualTo(3);
	}

	@Test
	void move_right_is_not_allowed_at_the_left_column() {
		// given
		Tile[][] initialPositions = {
				{new Tile(), new Tile(), new Tile(), new Tile()},
				{new Tile(), new Tile(), new Tile(), new Tile()},
				{new Tile(), new Tile(), new Tile(), new Tile()},
				{	   null, new Tile(), new Tile(), new Tile()}
		};
		Board board = new Board(initialPositions, moveServices);

		// when
		board.move(RIGHT);

		// then
		assertThat(board.getPositions()).isEqualTo(initialPositions);
		assertThat(board.getEmptyPosition().getValue0()).isEqualTo(3);
		assertThat(board.getEmptyPosition().getValue1()).isEqualTo(0);
	}

	@Test
	void user_is_win_when_the_order_is_from_smallest_to_largest() {
		// given
		Tile[][] initialPositions = {
				{new Tile(1), new Tile(2), new Tile(3), new Tile(4)},
				{new Tile(5), new Tile(6), new Tile(7), new Tile(8)},
				{new Tile(9), new Tile(10), new Tile(11), new Tile(12)},
				{new Tile(13), new Tile(14), 	   null, new Tile(15)}
		};

		Board board = new Board(initialPositions, moveServices);

		// when
		board.move(LEFT);

		// then
		assertThat(board.getGameState()).isEqualTo(GameState.WIN);
	}

	@Test
	void game_continues_when_the_order_is_not_from_smallest_to_largest() {
		// given
		Tile[][] initialPositions = {
				{new Tile(1), new Tile(2), new Tile(3), new Tile(4)},
				{new Tile(5), new Tile(6), new Tile(7), new Tile(8)},
				{new Tile(9), new Tile(10), new Tile(11), new Tile(12)},
				{new Tile(13), new Tile(15), 	   null, new Tile(14)}
		};

		Board board = new Board(initialPositions, moveServices);

		// when
		board.move(LEFT);

		// then
		assertThat(board.getGameState()).isEqualTo(GameState.CONTINUE);
	}
}