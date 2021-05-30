package ua.smolii.puzzle.presentation;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import lombok.SneakyThrows;
import ua.smolii.puzzle.model.Board;
import ua.smolii.puzzle.model.Direction;
import ua.smolii.puzzle.model.GameState;

@ExtendWith(MockitoExtension.class)
class InteractiveLoopTest {

	@Mock
	private MovesReader movesReader;
	@Mock
	private InterfacePrinter printer;
	@Mock
	private Board board;

	@InjectMocks
	private InteractiveLoop loop;

	@Test
	@SneakyThrows
	void print_startup_instructions_at_the_start_of_the_game() {
		// given
		given(board.getGameState()).willReturn(GameState.WIN);

		// when
		loop.startGame();

		// then
		verify(printer).printStartInstructions();
	}

	@Test
	void print_board_after_each_user_input() {

	}

	@Test
	@SneakyThrows
	void print_help_if_user_entered_invalid_direction() {
		// given
		given(movesReader.read()).willThrow(new InvalidDirectionException("Middle"));
		given(board.getGameState()).willReturn(GameState.WIN);

		// when
		loop.startGame();

		// then
		verify(printer).printHelp("There is no direction: Middle");
	}

	@Test
	@SneakyThrows
	void game_is_end_when_game_state_is_WIN() {
		// given
		given(board.getGameState()).willReturn(GameState.CONTINUE).willReturn(GameState.WIN);
		given(movesReader.read()).willReturn(Direction.DOWN);

		// when
		loop.startGame();

		// then
		verify(movesReader, times(2)).read();
		verify(board, times(2)).move(any(Direction.class));
	}

	@Test
	@SneakyThrows
	void print_board_and_congratulations_message_when_user_win() {
		// given
		given(board.getGameState()).willReturn(GameState.WIN);

		// when
		loop.startGame();

		// then
		verify(printer, times(2)).print(board); // first - in loop
		verify(printer).win();
	}
}