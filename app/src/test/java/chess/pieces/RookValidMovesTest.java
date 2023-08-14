package chess.pieces;

import chess.board.Color;
import chess.board.position.Movement;
import chess.board.position.Position;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RookValidMovesTest {
    @Test
    void shouldBeAbleToMoveVerticallyAndHorizontally() {
        Rook rook = new Rook(Color.BLACK, new Position("d4"));

        var expectedValidMoves = Set.of(
                new Movement("d4", "a4"),
                new Movement("d4", "b4"),
                new Movement("d4", "c4"),
                new Movement("d4", "e4"),
                new Movement("d4", "f4"),
                new Movement("d4", "g4"),
                new Movement("d4", "h4"),

                new Movement("d4", "d1"),
                new Movement("d4", "d2"),
                new Movement("d4", "d3"),
                new Movement("d4", "d5"),
                new Movement("d4", "d6"),
                new Movement("d4", "d7"),
                new Movement("d4", "d8")
        );

        assertEquals(expectedValidMoves, rook.getValidMoves());
    }
}
