package chess.pieces;

import chess.board.InMemoryPositionBoardPlacement;
import chess.plays.Displacement;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class QueenDisplacementTest {
    @Test
    public void shouldBeAbleToMoveHorizontalyVerticallyAndDiagonaly() {
        var queen = new Queen(Color.BLACK);
        queen.placeInBoard(new InMemoryPositionBoardPlacement("d4"));

        var expectedValidMoves = Set.of(
                new Displacement("d4", "a1"),
                new Displacement("d4", "b2"),
                new Displacement("d4", "c3"),
                new Displacement("d4", "e5"),
                new Displacement("d4", "f6"),
                new Displacement("d4", "g7"),
                new Displacement("d4", "h8"),

                new Displacement("d4", "a7"),
                new Displacement("d4", "b6"),
                new Displacement("d4", "c5"),
                new Displacement("d4", "e3"),
                new Displacement("d4", "f2"),
                new Displacement("d4", "g1"),

                new Displacement("d4", "d1"),
                new Displacement("d4", "d2"),
                new Displacement("d4", "d3"),
                new Displacement("d4", "d5"),
                new Displacement("d4", "d6"),
                new Displacement("d4", "d7"),
                new Displacement("d4", "d8"),

                new Displacement("d4", "a4"),
                new Displacement("d4", "b4"),
                new Displacement("d4", "c4"),
                new Displacement("d4", "e4"),
                new Displacement("d4", "f4"),
                new Displacement("d4", "g4"),
                new Displacement("d4", "h4")
        );

        assertEquals(expectedValidMoves, queen.getValidMoves());
    }
}
