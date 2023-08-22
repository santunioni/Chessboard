package chess.pieces;

import chess.board.BoardState;
import chess.board.position.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RookThreatensTest {
    private BoardState board;

    @BeforeEach
    void setUp() {
        this.board = new BoardState();
    }

    @Test
    public void shouldThreatenVertically() {
        var rook = new Rook(Color.BLACK);
        board.placePiece("d4", rook);

        assertTrue(rook.couldAttackIfOccupiedByEnemy(new Position("d5")));
    }

    @Test
    public void shouldThreatenHorizontally() {
        var rook = new Rook(Color.BLACK);
        board.placePiece("d4", rook);

        assertTrue(rook.couldAttackIfOccupiedByEnemy(new Position("e4")));
    }

    @Test
    public void shouldNotThreatenIfPieceBetween() {
        var rook = new Rook(Color.BLACK);
        board.placePiece("d4", rook);
        board.placePiece("d5", new Pawn(Color.BLACK));

        assertFalse(rook.couldAttackIfOccupiedByEnemy(new Position("d6")));
    }

    @Test
    public void shouldThreatenIfPieceAtPosition() {
        var rook = new Rook(Color.BLACK);
        board.placePiece("d4", rook);
        board.placePiece("d5", new Pawn(Color.BLACK));

        assertTrue(rook.couldAttackIfOccupiedByEnemy(new Position("d5")));
    }
}
