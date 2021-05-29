package ua.smolii.puzzle.services.generator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.BDDMockito.given;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import ua.smolii.puzzle.model.Tile;

@ExtendWith(MockitoExtension.class)
class BoardGenerationServiceTest {

	@Mock
	private SolvabilityValidator validator;

	@InjectMocks
	private BoardGenerationService service;

	@Test
	void generate_4x4_puzzle() {
		// given
		given(validator.isSolvable(anyList())).willReturn(true);

		// when
		Tile[][] actual = service.generatePositions(4);

		// then
		List<Tile> actualList = Arrays.stream(actual).flatMap(Arrays::stream).collect(Collectors.toList());
		assertThat(actualList).containsExactlyInAnyOrder(
				new Tile(1), new Tile(2), new Tile(3), new Tile(4),
				new Tile(5), new Tile(6), new Tile(7), new Tile(8),
				new Tile(9), new Tile(10), new Tile(11), new Tile(12),
				new Tile(13), new Tile(14), new Tile(15), null
		);
	}

	@Test
	void generate_5x5_puzzle() {
		// given
		given(validator.isSolvable(anyList())).willReturn(true);

		// when
		Tile[][] actual = service.generatePositions(5);

		// then
		List<Tile> actualList = Arrays.stream(actual).flatMap(Arrays::stream).collect(Collectors.toList());
		assertThat(actualList).containsExactlyInAnyOrder(
				new Tile(1), new Tile(2), new Tile(3), new Tile(4), new Tile(5),
				new Tile(6), new Tile(7), new Tile(8), new Tile(9), new Tile(10),
				new Tile(11), new Tile(12), new Tile(13), new Tile(14), new Tile(15),
				new Tile(16), new Tile(17), new Tile(18), new Tile(19), new Tile(20),
				new Tile(21), new Tile(22), new Tile(23), new Tile(24), null
		);
	}
}