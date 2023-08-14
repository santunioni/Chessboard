package chess.pieces;

import chess.board.Color;
import chess.plays.Displacement;
import chess.board.position.Position;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PawnDisplacementTest {
    @Test
    void shouldBeAbleToMoveExactlyOneSquareUp() {
        Pawn pawn = new Pawn(Color.WHITE, new Position("a4"));

        var expectedValidMoves = Set.of(
                new Displacement("a4", "a5")
        );

        assertEquals(expectedValidMoves, pawn.getValidMoves());
    }

    @Test
    void shouldBeAbleToMoveExactlyOneSquareDown() {
        Pawn pawn = new Pawn(Color.BLACK, new Position("b4"));

        var expectedValidMoves = Set.of(
                new Displacement("b4", "b3")
        );

        assertEquals(expectedValidMoves, pawn.getValidMoves());
    }

    @Test
    void shouldBeAbleToMoveExactlyTwoSquaresUpIfHasNotMovedYet() {
        Pawn pawn = new Pawn(Color.WHITE, new Position("c2"));

        var expectedValidMoves = Set.of(
                new Displacement("c2", "c3"),
                new Displacement("c2", "c4")
        );

        assertEquals(expectedValidMoves, pawn.getValidMoves());
    }

    @Test
    void shouldBeAbleToMoveExactlyTwoSquaresDownIfHasNotMovedYet() {
        Pawn pawn = new Pawn(Color.BLACK, new Position("d7"));

        var expectedValidMoves = Set.of(
                new Displacement("d7", "d6"),
                new Displacement("d7", "d5")
        );

        assertEquals(expectedValidMoves, pawn.getValidMoves());
    }
}
