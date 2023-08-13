package chess.pieces;

import chess.board.Movement;
import chess.board.PlayerSide;
import chess.board.Position;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class KingValidMovesTest {
    @Test
    void shouldBeAbleToMoveExactlyOneSquareInAnyDirection() {
        King king = new King(PlayerSide.BLACK, new Position("d4"));

        var expectedValidMoves = new HashSet<>(Set.of(
                new Movement("d4", "c3"),
                new Movement("d4", "d3"),
                new Movement("d4", "e3"),

                new Movement("d4", "c4"),
                new Movement("d4", "e4"),

                new Movement("d4", "c5"),
                new Movement("d4", "d5"),
                new Movement("d4", "e5")
        ));

        assertEquals(expectedValidMoves, king.getValidMoves());
    }

    @Test
    void shouldBeBlockedByBoardWalls() {
        King king = new King(PlayerSide.WHITE, new Position("e1"));

        var expectedValidMoves = new HashSet<>(Set.of(
                new Movement("e1", "d1"),
                new Movement("e1", "f1"),

                new Movement("e1", "d2"),
                new Movement("e1", "e2"),
                new Movement("e1", "f2")
        ));

        assertEquals(expectedValidMoves, king.getValidMoves());
    }

    @Test
    void shouldBeBlockedByBottomLeftCorner() {
        King king = new King(PlayerSide.WHITE, new Position("a1"));

        var expectedValidMoves = new HashSet<>(Set.of(
                new Movement("a1", "b1"),
                new Movement("a1", "a2"),
                new Movement("a1", "b2")
        ));

        assertEquals(expectedValidMoves, king.getValidMoves());
    }

    @Test
    void shouldBeBlockedByBottomRightCorner() {
        King king = new King(PlayerSide.WHITE, new Position("h1"));

        var expectedValidMoves = new HashSet<>(Set.of(
                new Movement("h1", "g1"),
                new Movement("h1", "g2"),
                new Movement("h1", "h2")
        ));

        assertEquals(expectedValidMoves, king.getValidMoves());
    }

    @Test
    void shouldBeBlockedByTopLeftCorner() {
        King king = new King(PlayerSide.WHITE, new Position("a8"));

        var expectedValidMoves = new HashSet<>(Set.of(
                new Movement("a8", "a7"),
                new Movement("a8", "b7"),
                new Movement("a8", "b8")
        ));

        assertEquals(expectedValidMoves, king.getValidMoves());
    }

    @Test
    void shouldBeBlockedByTopRightCorner() {
        King king = new King(PlayerSide.WHITE, new Position("h8"));

        var expectedValidMoves = new HashSet<>(Set.of(
                new Movement("h8", "g7"),
                new Movement("h8", "h7"),
                new Movement("h8", "g8")
        ));

        assertEquals(expectedValidMoves, king.getValidMoves());
    }
}
