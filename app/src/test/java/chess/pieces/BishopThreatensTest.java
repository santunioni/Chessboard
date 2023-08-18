package chess.pieces;

import chess.board.BoardState;
import chess.board.position.Position;
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

        assertTrue(bishop.threatens(new Position("f6")));
    }

    @Test
    public void shouldNotThreatenIfEnemyIsBetween() {
        var bishop = new Bishop(Color.BLACK);
        this.board.placePiece("d4", bishop);
        this.board.placePiece("f6", new Pawn(Color.WHITE));

        assertFalse(bishop.threatens(new Position("g7")));
    }

    @Test
    public void shouldNotThreatenIfAllyIsBetween() {
        var bishop = new Bishop(Color.BLACK);
        this.board.placePiece("d4", bishop);
        this.board.placePiece("f6", new Pawn(Color.BLACK));

        assertFalse(bishop.threatens(new Position("g7")));
    }

    @Test
    public void shouldThreatenEvenIfPositionIsOccupiedByAlly() {
        var bishop = new Bishop(Color.BLACK);
        this.board.placePiece("d4", bishop);
        this.board.placePiece("f6", new Pawn(Color.BLACK));

        assertTrue(bishop.threatens(new Position("f6")));
    }

    @Test
    public void shouldThreatenEvenIfPositionIsOccupiedByEnemy() {
        var bishop = new Bishop(Color.BLACK);
        this.board.placePiece("d4", bishop);
        this.board.placePiece("f6", new Pawn(Color.WHITE));

        assertTrue(bishop.threatens(new Position("f6")));
    }

    @Test
    public void shouldNotThreatenVertically() {
        var bishop = new Bishop(Color.BLACK);
        this.board.placePiece("d4", bishop);

        assertFalse(bishop.threatens(new Position("d5")));
    }

    @Test
    public void shouldNotThreatenHorizontally() {
        var bishop = new Bishop(Color.BLACK);
        this.board.placePiece("d4", bishop);

        assertFalse(bishop.threatens(new Position("e4")));
    }
}
