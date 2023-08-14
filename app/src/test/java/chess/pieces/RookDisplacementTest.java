package chess.pieces;

import chess.board.Color;
import chess.plays.Displacement;
import chess.board.position.Position;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RookDisplacementTest {
    @Test
    void shouldBeAbleToMoveVerticallyAndHorizontally() {
        Rook rook = new Rook(Color.BLACK, new Position("d4"));

        var expectedValidMoves = Set.of(
                new Displacement("d4", "a4"),
                new Displacement("d4", "b4"),
                new Displacement("d4", "c4"),
                new Displacement("d4", "e4"),
                new Displacement("d4", "f4"),
                new Displacement("d4", "g4"),
                new Displacement("d4", "h4"),

                new Displacement("d4", "d1"),
                new Displacement("d4", "d2"),
                new Displacement("d4", "d3"),
                new Displacement("d4", "d5"),
                new Displacement("d4", "d6"),
                new Displacement("d4", "d7"),
                new Displacement("d4", "d8")
        );

        assertEquals(expectedValidMoves, rook.getValidMoves());
    }
}
