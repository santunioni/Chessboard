package chess.pieces;

import chess.board.BoardState;
import chess.board.position.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PawnDisplacementTest {

    private BoardState board;

    @BeforeEach
    void setUp() {
        this.board = new BoardState();
    }

    @Test
    void shouldBeAbleToMoveExactlyOneSquareUp() {
        var pawn = new Pawn(Color.WHITE);
        this.board.placePiece("a4", pawn);

        assertTrue(pawn.couldMoveToIfEmpty(new Position("a5")));
    }

    @Test
    void shouldBeAbleToMoveExactlyOneSquareDown() {
        var pawn = new Pawn(Color.BLACK);
        this.board.placePiece("b4", pawn);

        assertTrue(pawn.couldMoveToIfEmpty(new Position("b3")));
    }

    @Test
    void shouldBeAbleToMoveExactlyTwoSquaresUpIfHasNotMovedYet() {
        var pawn = new Pawn(Color.WHITE);
        this.board.placePiece("c2", pawn);

        assertTrue(pawn.couldMoveToIfEmpty(new Position("c3")));
        assertTrue(pawn.couldMoveToIfEmpty(new Position("c4")));
    }

    @Test
    void shouldBeAbleToMoveExactlyTwoSquaresDownIfHasNotMovedYet() {
        var pawn = new Pawn(Color.BLACK);
        this.board.placePiece("d7", pawn);

        assertTrue(pawn.couldMoveToIfEmpty(new Position("d6")));
        assertTrue(pawn.couldMoveToIfEmpty(new Position("d5")));
    }

    @Test
    void shouldBeBlockedByOtherPieces() {
        var pawn = new Pawn(Color.WHITE);
        this.board.placePiece("e2", pawn);
        this.board.placePiece("e3", new Pawn(Color.BLACK));

        assertTrue(pawn.couldMoveToIfEmpty(new Position("e3")));
        assertFalse(pawn.couldMoveToIfEmpty(new Position("e4")));
    }
}
