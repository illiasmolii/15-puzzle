package ua.smolii.puzzle.presentation;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;

import java.io.BufferedReader;
import java.util.stream.Stream;

import org.assertj.core.api.ThrowableAssert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import lombok.SneakyThrows;
import ua.smolii.puzzle.model.Direction;

@ExtendWith(MockitoExtension.class)
class MovesReaderTest {

	@Mock
	private BufferedReader reader;

	@InjectMocks
	private MovesReader movesReader;

	@ParameterizedTest
	@MethodSource("directions")
	@SneakyThrows
	void parse_directions_from_user(String input, Direction expected) {
		// given
		given(reader.readLine()).willReturn(input);

		// when
		Direction actual = movesReader.read();

		// then
		assertThat(actual).isEqualTo(expected);
	}

	private static Stream<Arguments> directions() {
		return Stream.of(
				Arguments.of("W", Direction.UP),
				Arguments.of("w", Direction.UP),
				Arguments.of("S", Direction.DOWN),
				Arguments.of("s", Direction.DOWN),
				Arguments.of("A", Direction.LEFT),
				Arguments.of("a", Direction.LEFT),
				Arguments.of("D", Direction.RIGHT),
				Arguments.of("d", Direction.RIGHT)
		);
	}

	@Test
	@SneakyThrows
	void throws_exception_on_invalid_input() {
		// given
		given(reader.readLine()).willReturn("UNKNOWN");

		// when
		ThrowableAssert.ThrowingCallable methodCall = () -> movesReader.read();

		// then
		assertThatThrownBy(methodCall).isInstanceOf(InvalidDirectionException.class);
	}
}
