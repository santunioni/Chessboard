package chess.board.position;

import chess.board.path.BoardPathDirection;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;


class PositionDirectionToCases implements ArgumentsProvider {
    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
        return Stream.of(
                Arguments.of("d4", "d5", BoardPathDirection.VERTICAL_UP),
                Arguments.of("d4", "d3", BoardPathDirection.VERTICAL_DOWN),
                Arguments.of("d4", "e4", BoardPathDirection.HORIZONTAL_RIGHT),
                Arguments.of("d4", "c4", BoardPathDirection.HORIZONTAL_LEFT),
                Arguments.of("d4", "e5", BoardPathDirection.DIAGONAL_UP_RIGHT),
                Arguments.of("d4", "e3", BoardPathDirection.DIAGONAL_DOWN_RIGHT),
                Arguments.of("d4", "c3", BoardPathDirection.DIAGONAL_DOWN_LEFT),
                Arguments.of("d4", "c5", BoardPathDirection.DIAGONAL_UP_LEFT)
        );
    }
}


public class PositionDirectionToTest {
    @ParameterizedTest
    @ArgumentsSource(PositionDirectionToCases.class)
    void shouldReturnRankStringRepresentation(String from, String to, BoardPathDirection expectedDirection) {
        assertEquals(expectedDirection, new Position(from).directionTo(new Position(to)).orElseThrow());
    }

    @Test
    void shouldReturnNullWhenPositionsAreNotInSameLine() {
        assertNull(new Position("d4").directionTo(new Position("e6")).orElse(null));
    }

    @Test
    void shouldReturnNullWhenPositionsAreSame() {
        assertNull(new Position("d4").directionTo(new Position("d4")).orElse(null));
    }
}
