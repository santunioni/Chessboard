package chess.pieces;

import chess.board.BoardState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BishopThreatensTest {

    private BoardState board;

    @BeforeEach
    void setUp() {
        this.board = new BoardState();
    }


    @Test
    public void shouldThreatenDiagonally() {
        var bishop = new Bishop(Color.BLACK);
        this.board.placePiece("d4", bishop);

        assertTrue(bishop.threatens("f6"));
    }

    @Test
    public void shouldNotThreatenIfEnemyIsBetween() {
        var bishop = new Bishop(Color.BLACK);
        this.board.placePiece("d4", bishop);
        this.board.placePiece("f6", new Pawn(Color.WHITE));

        assertFalse(bishop.threatens("g7"));
    }

    @Test
    public void shouldNotThreatenIfAllyIsBetween() {
        var bishop = new Bishop(Color.BLACK);
        this.board.placePiece("d4", bishop);
        this.board.placePiece("f6", new Pawn(Color.BLACK));

        assertFalse(bishop.threatens("g7"));
    }

    @Test
    public void shouldNotThreatenVertically() {
        var bishop = new Bishop(Color.BLACK);
        this.board.placePiece("d4", bishop);

        assertFalse(bishop.threatens("d5"));
    }

    @Test
    public void shouldNotThreatenHorizontally() {
        var bishop = new Bishop(Color.BLACK);
        this.board.placePiece("d4", bishop);

        assertFalse(bishop.threatens("e4"));
    }
}
