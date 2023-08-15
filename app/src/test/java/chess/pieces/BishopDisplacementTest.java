package chess.pieces;

import chess.board.BoardState;
import chess.plays.Displacement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BishopDisplacementTest {

    private BoardState board;

    @BeforeEach
    void setUp() {
        this.board = new BoardState();
    }

    @Test
    void shouldBeAbleToMoveDiagonally() {
        var bishop = new Bishop(Color.BLACK);
        this.board.placePiece("d4", bishop);

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

    @Test
    void shouldBeBlockedBySameColorPieces() {
        var bishop = new Bishop(Color.WHITE);
        this.board.placePiece("b1", bishop);
        this.board.placePiece("d3", new Pawn(Color.WHITE));

        var expectedValidMoves = Set.of(
                new Displacement("b1", "a2"),
                new Displacement("b1", "c2")
        );

        assertEquals(expectedValidMoves, bishop.getValidMoves());
    }
}
