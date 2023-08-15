package chess.board;

import chess.pieces.King;
import chess.pieces.Queen;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

public class BoardStateFactoryTest {
    @Test
    void shouldPlaceWhiteQueenOnD1() {
        var board = new BoardStateFactory().createFreshBoardState();
        var piece = (Queen) board.getPieceAt("d1").orElseThrow();

        assertInstanceOf(Queen.class, piece);
        assertEquals(Color.WHITE, piece.getColor());
    }

    @Test
    void shouldPlaceBlackQueenOnD8() {
        var board = new BoardStateFactory().createFreshBoardState();
        var piece = (Queen) board.getPieceAt("d8").orElseThrow();

        assertInstanceOf(Queen.class, piece);
        assertEquals(Color.BLACK, piece.getColor());
    }


    @Test
    void shouldPlaceWhiteKingOnE1() {
        var board = new BoardStateFactory().createFreshBoardState();
        var piece = (King) board.getPieceAt("e1").orElseThrow();

        assertInstanceOf(King.class, piece);
        assertEquals(Color.WHITE, piece.getColor());
    }

    @Test
    void shouldPlaceBlackKingOnE8() {
        var board = new BoardStateFactory().createFreshBoardState();
        var piece = (King) board.getPieceAt("e8").orElseThrow();

        assertInstanceOf(King.class, piece);
        assertEquals(Color.BLACK, piece.getColor());
    }
}
