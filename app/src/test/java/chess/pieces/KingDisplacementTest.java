package chess.pieces;

import chess.board.Color;
import chess.board.InMemoryPositionBoardPlacement;
import chess.plays.Displacement;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class KingDisplacementTest {
    @Test
    void shouldBeAbleToMoveExactlyOneSquareInAnyDirection() {
        var king = new King(Color.BLACK);
        king.placeInBoard(new InMemoryPositionBoardPlacement("d4"));

        var expectedValidMoves = Set.of(
                new Displacement("d4", "c3"),
                new Displacement("d4", "d3"),
                new Displacement("d4", "e3"),

                new Displacement("d4", "c4"),
                new Displacement("d4", "e4"),

                new Displacement("d4", "c5"),
                new Displacement("d4", "d5"),
                new Displacement("d4", "e5")
        );

        assertEquals(expectedValidMoves, king.getValidMoves());
    }

    @Test
    void shouldBeBlockedByWalls() {
        var king = new King(Color.WHITE);
        king.placeInBoard(new InMemoryPositionBoardPlacement("e1"));

        var expectedValidMoves = Set.of(
                new Displacement("e1", "d1"),
                new Displacement("e1", "f1"),

                new Displacement("e1", "d2"),
                new Displacement("e1", "e2"),
                new Displacement("e1", "f2")
        );

        assertEquals(expectedValidMoves, king.getValidMoves());
    }

    @Test
    void shouldBeBlockedByCorner() {
        var king = new King(Color.WHITE);
        king.placeInBoard(new InMemoryPositionBoardPlacement("a1"));

        var expectedValidMoves = Set.of(
                new Displacement("a1", "b1"),
                new Displacement("a1", "a2"),
                new Displacement("a1", "b2")
        );

        assertEquals(expectedValidMoves, king.getValidMoves());
    }
}
