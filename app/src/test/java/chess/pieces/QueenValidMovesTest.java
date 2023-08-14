package chess.pieces;

import chess.board.Color;
import chess.board.position.Movement;
import chess.board.position.Position;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class QueenValidMovesTest {
    @Test
    public void shouldBeAbleToMoveHorizontalyVerticallyAndDiagonaly() {
        var queen = new Queen(Color.BLACK, new Position("d4"));

        var expectedValidMoves = Set.of(
                new Movement("d4", "a1"),
                new Movement("d4", "b2"),
                new Movement("d4", "c3"),
                new Movement("d4", "e5"),
                new Movement("d4", "f6"),
                new Movement("d4", "g7"),
                new Movement("d4", "h8"),

                new Movement("d4", "a7"),
                new Movement("d4", "b6"),
                new Movement("d4", "c5"),
                new Movement("d4", "e3"),
                new Movement("d4", "f2"),
                new Movement("d4", "g1"),

                new Movement("d4", "d1"),
                new Movement("d4", "d2"),
                new Movement("d4", "d3"),
                new Movement("d4", "d5"),
                new Movement("d4", "d6"),
                new Movement("d4", "d7"),
                new Movement("d4", "d8"),

                new Movement("d4", "a4"),
                new Movement("d4", "b4"),
                new Movement("d4", "c4"),
                new Movement("d4", "e4"),
                new Movement("d4", "f4"),
                new Movement("d4", "g4"),
                new Movement("d4", "h4")
        );

        assertEquals(expectedValidMoves, queen.getValidMoves());
    }
}
