package chess.pieces;

import chess.board.BoardState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class KingThreatensTest {
    private BoardState board;

    @BeforeEach
    void setUp() {
        this.board = new BoardState();
    }

    @Test
    public void shouldThreatenVertically() {
        var king = new King(Color.BLACK);
        board.placePiece("d4", king);

        assertTrue(king.threatens("d5"));
    }

    @Test
    public void shouldThreatenHorizontally() {
        var king = new King(Color.BLACK);
        board.placePiece("d4", king);

        assertTrue(king.threatens("e4"));
    }

    @Test
    public void shouldThreatenDiagonally() {
        var king = new King(Color.BLACK);
        board.placePiece("d4", king);

        assertTrue(king.threatens("e5"));
    }

    @Test
    public void shouldNotDistantPiece() {
        var king = new King(Color.BLACK);
        board.placePiece("d4", king);

        assertFalse(king.threatens("d6"));
    }

}
