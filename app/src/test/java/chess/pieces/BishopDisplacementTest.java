package chess.pieces;

import chess.board.Color;
import chess.board.position.Position;
import chess.plays.Displacement;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BishopDisplacementTest {

    @Test
    void shouldBeAbleToMoveDiagonally() {
        var bishop = new Bishop(Color.BLACK, new Position("d4"));

        var expectedValidMoves = Set.of(
                new Displacement("d4", "c3"),
                new Displacement("d4", "b2"),
                new Displacement("d4", "a1"),

                new Displacement("d4", "c5"),
                new Displacement("d4", "b6"),
                new Displacement("d4", "a7"),

                new Displacement("d4", "e3"),
                new Displacement("d4", "f2"),
                new Displacement("d4", "g1"),

                new Displacement("d4", "e5"),
                new Displacement("d4", "f6"),
                new Displacement("d4", "g7"),
                new Displacement("d4", "h8")
        );

        assertEquals(expectedValidMoves, bishop.getValidMoves());
    }
}