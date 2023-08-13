package chess.pieces;

import chess.board.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.stream.Stream;

class FileStringRepresentationCases implements ArgumentsProvider {
    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
        return Stream.of(
                Arguments.of(File.A, "a"),
                Arguments.of(File.B, "b"),
                Arguments.of(File.C, "c"),
                Arguments.of(File.D, "d"),
                Arguments.of(File.E, "e"),
                Arguments.of(File.F, "f"),
                Arguments.of(File.G, "g"),
                Arguments.of(File.H, "h")
        );
    }
}

public class KingValidMovesTest {
    @Test
    void shouldBeAbleToMoveExactlyOneSquareInAnyDirection() {
        King king = new King(PlayerSide.BLACK, new Position(File.D, Rank.FOUR));

        var validMoves = king.getValidMoves();

        assert validMoves.contains(new Movement("d4", "d5"));
    }
}
