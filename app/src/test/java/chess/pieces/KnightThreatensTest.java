package chess.pieces;

import chess.board.BoardState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class KnightThreatensCases implements ArgumentsProvider {
    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
        return Stream.of(
                Arguments.of("d4", "e6"),
                Arguments.of("d4", "c6"),
                Arguments.of("d4", "b5"),
                Arguments.of("d4", "b3"),
                Arguments.of("d4", "c2"),
                Arguments.of("d4", "e2"),
                Arguments.of("d4", "f3"),
                Arguments.of("d4", "f5")
        );
    }
}

class KnightNotThreatensCases implements ArgumentsProvider {
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

public class KnightThreatensTest {
    private BoardState board;

    @BeforeEach
    void setUp() {
        this.board = new BoardState();
    }

    @ParameterizedTest
    @ArgumentsSource(KnightThreatensCases.class)
    void shouldThreatenPosition(String knightPosition, String threatenedPosition) {
        var knight = new Knight(Color.WHITE);
        this.board.placePiece(knightPosition, knight);
        assertTrue(knight.threatens(threatenedPosition));
    }

    @ParameterizedTest
    @ArgumentsSource(KnightNotThreatensCases.class)
    void shouldNotThreatenPosition(String knightPosition, String threatenedPosition) {
        var knight = new Knight(Color.WHITE);
        this.board.placePiece(knightPosition, knight);
        assertFalse(knight.threatens(threatenedPosition));
    }
}
