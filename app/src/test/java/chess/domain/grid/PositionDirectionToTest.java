package chess.domain.grid;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class PositionDirectionToTest {
    @ParameterizedTest
    @ArgumentsSource(PositionDirectionToCases.class)
    void shouldReturnRankStringRepresentation(String from, String to,
                                              Direction expectedDirection) {
        assertEquals(expectedDirection, new Position(from).directionTo(new Position(to)).orElseThrow());
    }

    @Test
    void shouldReturnNullWhenPositionsAreNotInSameLine() {
        assertTrue(new Position("d4").directionTo(new Position("e6")).isEmpty());
    }

    @Test
    void shouldReturnNullWhenPositionsAreSame() {
        assertTrue(new Position("d4").directionTo(new Position("d4")).isEmpty());
    }

    static class PositionDirectionToCases implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
            return Stream.of(
                    Arguments.of("d4", "d5", Direction.VERTICAL_UP),
                    Arguments.of("d4", "d3", Direction.VERTICAL_DOWN),
                    Arguments.of("d4", "e4", Direction.HORIZONTAL_RIGHT),
                    Arguments.of("d4", "c4", Direction.HORIZONTAL_LEFT),
                    Arguments.of("d4", "e5", Direction.DIAGONAL_UP_RIGHT),
                    Arguments.of("d4", "e3", Direction.DIAGONAL_DOWN_RIGHT),
                    Arguments.of("d4", "c3", Direction.DIAGONAL_DOWN_LEFT),
                    Arguments.of("d4", "c5", Direction.DIAGONAL_UP_LEFT)
            );
        }
    }
}
