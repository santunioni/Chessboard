package chess.board.position;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PositionNeighborCases implements ArgumentsProvider {
    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
        return Stream.of(
                Arguments.of("d4", "d5"),
                Arguments.of("d4", "d3"),
                Arguments.of("d4", "e4"),
                Arguments.of("d4", "c4"),
                Arguments.of("d4", "e5"),
                Arguments.of("d4", "e3"),
                Arguments.of("d4", "c3"),
                Arguments.of("d4", "c5")
        );
    }
}

class PositionNotNeighborCases implements ArgumentsProvider {
    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
        return Stream.of(
                Arguments.of("d4", "d6"),
                Arguments.of("d4", "d2"),
                Arguments.of("d4", "f4"),
                Arguments.of("d4", "b4"),
                Arguments.of("d4", "f6"),
                Arguments.of("d4", "f2"),
                Arguments.of("d4", "b2"),
                Arguments.of("d4", "b6")
        );
    }
}

public class PositionNeighborToTest {
    @ParameterizedTest
    @ArgumentsSource(PositionNeighborCases.class)
    void shouldClassifyAsNeighbor(Position from, Position to) {
        assertTrue(from.isNeighborTo(to));
    }

    @ParameterizedTest
    @ArgumentsSource(PositionNotNeighborCases.class)
    void shouldClassifyAsNotNeighbor(Position from, Position to) {
        assertFalse(from.isNeighborTo(to));
    }
}
