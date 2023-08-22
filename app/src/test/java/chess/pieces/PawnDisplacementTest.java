package chess.pieces;

import chess.board.BoardState;
import chess.board.position.Position;
import chess.plays.Displacement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
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

        assertTrue(pawn.canMoveTo(new Position("a5")));
    }

    @Test
    void shouldBeAbleToMoveExactlyOneSquareDown() {
        var pawn = new Pawn(Color.BLACK);
        this.board.placePiece("b4", pawn);

        var expectedValidMoves = Set.of(
                new Displacement("b4", "b3")
        );

        assertEquals(expectedValidMoves, pawn.getValidMoves());
    }

    @Test
    void shouldBeAbleToMoveExactlyTwoSquaresUpIfHasNotMovedYet() {
        var pawn = new Pawn(Color.WHITE);
        this.board.placePiece("c2", pawn);

        var expectedValidMoves = Set.of(
                new Displacement("c2", "c3"),
                new Displacement("c2", "c4")
        );

        assertEquals(expectedValidMoves, pawn.getValidMoves());
    }

    @Test
    void shouldBeAbleToMoveExactlyTwoSquaresDownIfHasNotMovedYet() {
        var pawn = new Pawn(Color.BLACK);
        this.board.placePiece("d7", pawn);

        var expectedValidMoves = Set.of(
                new Displacement("d7", "d6"),
                new Displacement("d7", "d5")
        );

        assertEquals(expectedValidMoves, pawn.getValidMoves());
    }

    @Test
    void shouldBeBlockedByOtherPieces() {
        var pawn = new Pawn(Color.WHITE);
        this.board.placePiece("e2", pawn);
        this.board.placePiece("e4", new Pawn(Color.BLACK));

        var expectedValidMoves = Set.of(
                new Displacement("e2", "e3")
        );

        assertEquals(expectedValidMoves, pawn.getValidMoves());
    }

    @Test
    void shouldBeBlockedByNeighborPiece() {
        var pawn = new Pawn(Color.WHITE);
        this.board.placePiece("e2", pawn);
        this.board.placePiece("e3", new Pawn(Color.BLACK));

        assertEquals(0, pawn.getValidMoves().size());
    }
}
