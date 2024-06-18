package chess.domain.pieces;

import chess.domain.board.Board;
import chess.domain.board.PieceColor;
import chess.domain.board.PieceFactory;
import chess.domain.grid.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RookThreatensTest {
    private final PieceFactory pieceFactory = new PieceFactory();
    private Board board;

    @BeforeEach
    void setUp() {
        this.board = new Board();
    }

    @Test
    public void shouldThreatenVertically() {
        var rook = this.pieceFactory.createRooks(PieceColor.BLACK).get(0);
        board.placePieceAt("d4", rook);

        assertTrue(rook.threatens(new Position("d5")));
    }

    @Test
    public void shouldThreatenHorizontally() {
        var rook = this.pieceFactory.createRooks(PieceColor.BLACK).get(0);
        board.placePieceAt("d4", rook);

        assertTrue(rook.threatens(new Position("e4")));
    }

    @Test
    public void shouldNotThreatenIfPieceBetween() {
        var rook = this.pieceFactory.createRooks(PieceColor.BLACK).get(0);
        board.placePieceAt("d4", rook);
        board.placePieceAt("d5", this.pieceFactory.createPawns(PieceColor.BLACK).get(0));

        assertFalse(rook.threatens(new Position("d6")));
    }

    @Test
    public void shouldThreatenIfPieceAtPosition() {
        var rook = this.pieceFactory.createRooks(PieceColor.BLACK).get(0);
        board.placePieceAt("d4", rook);
        board.placePieceAt("d5", this.pieceFactory.createPawns(PieceColor.BLACK).get(0));

        assertTrue(rook.threatens(new Position("d5")));
    }
}
