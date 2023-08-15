package chess.pieces;

import chess.board.InMemoryPositionBoardPlacement;
import chess.plays.Displacement;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PawnDisplacementTest {
    @Test
    void shouldBeAbleToMoveExactlyOneSquareUp() {
        var pawn = new Pawn(Color.WHITE);
        pawn.placeInBoard(new InMemoryPositionBoardPlacement("a4"));

        var expectedValidMoves = Set.of(
                new Displacement("a4", "a5")
        );

        assertEquals(expectedValidMoves, pawn.getValidMoves());
    }

    @Test
    void shouldBeAbleToMoveExactlyOneSquareDown() {
        var pawn = new Pawn(Color.BLACK);
        pawn.placeInBoard(new InMemoryPositionBoardPlacement("b4"));

        var expectedValidMoves = Set.of(
                new Displacement("b4", "b3")
        );

        assertEquals(expectedValidMoves, pawn.getValidMoves());
    }

    @Test
    void shouldBeAbleToMoveExactlyTwoSquaresUpIfHasNotMovedYet() {
        var pawn = new Pawn(Color.WHITE);
        pawn.placeInBoard(new InMemoryPositionBoardPlacement("c2"));

        var expectedValidMoves = Set.of(
                new Displacement("c2", "c3"),
                new Displacement("c2", "c4")
        );

        assertEquals(expectedValidMoves, pawn.getValidMoves());
    }

    @Test
    void shouldBeAbleToMoveExactlyTwoSquaresDownIfHasNotMovedYet() {
        var pawn = new Pawn(Color.BLACK);
        pawn.placeInBoard(new InMemoryPositionBoardPlacement("d7"));

        var expectedValidMoves = Set.of(
                new Displacement("d7", "d6"),
                new Displacement("d7", "d5")
        );

        assertEquals(expectedValidMoves, pawn.getValidMoves());
    }
}
