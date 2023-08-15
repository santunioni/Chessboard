package chess.pieces;

import chess.board.BoardState;
import chess.plays.Displacement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class KingDisplacementTest {
    private BoardState board;

    @BeforeEach
    void setUp() {
        this.board = new BoardState();
    }

    @Test
    void shouldBeAbleToMoveExactlyOneSquareInAnyDirection() {
        var king = new King(Color.BLACK);
        this.board.placePiece("d4", king);

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
        this.board.placePiece("e1", king);

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
        this.board.placePiece("a1", king);

        var expectedValidMoves = Set.of(
                new Displacement("a1", "b1"),
                new Displacement("a1", "a2"),
                new Displacement("a1", "b2")
        );

        assertEquals(expectedValidMoves, king.getValidMoves());
    }

    @Test
    void shouldBeBlockedByItsTeamMates() {
        var king = new King(Color.WHITE);
        this.board.placePiece("a1", king);
        this.board.placePiece("a2", new Pawn(Color.WHITE));
        this.board.placePiece("b2", new Pawn(Color.WHITE));

        var expectedValidMoves = Set.of(
                new Displacement("a1", "b1")
        );

        assertEquals(expectedValidMoves, king.getValidMoves());
    }
}
