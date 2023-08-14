package chess.pieces;

import chess.board.Color;
import chess.board.position.Movement;
import chess.board.position.Position;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BishopValidMovesTest {

    @Test
    void shouldBeAbleToMoveDiagonally() {
        var bishop = new Bishop(Color.BLACK, new Position("d4"));

        var expectedValidMoves = new HashSet<>(Set.of(
                new Movement("d4", "c3"),
                new Movement("d4", "b2"),
                new Movement("d4", "a1"),

                new Movement("d4", "c5"),
                new Movement("d4", "b6"),
                new Movement("d4", "a7"),

                new Movement("d4", "e3"),
                new Movement("d4", "f2"),
                new Movement("d4", "g1"),

                new Movement("d4", "e5"),
                new Movement("d4", "f6"),
                new Movement("d4", "g7"),
                new Movement("d4", "h8")
        ));

        assertEquals(expectedValidMoves, bishop.getValidMoves());
    }
}