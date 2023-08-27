package chess.game.plays;

import chess.game.board.BoardState;
import chess.game.grid.Position;
import chess.game.pieces.Color;
import chess.game.pieces.Pawn;
import chess.game.pieces.Queen;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CaptureTest {
    private BoardState board;

    @BeforeEach
    void setUp() {
        this.board = new BoardState();
    }

    @Test
    void shouldAllowWhitePawnDiagonalUpLeftAttack() throws IlegalPlay {
        var pawn = new Pawn(Color.WHITE);
        board.placePiece("e2", pawn);
        board.placePiece("d3", new Pawn(Color.BLACK));

        var attack = new Capture(new Position("e2"), new Position("d3"));
        attack.actUpon(board);

        assertNull(board.getPieceAt(new Position("e2")).orElse(null));
        assertEquals(pawn, board.getPieceAt(new Position("d3")).orElseThrow());
    }

    @Test
    void shouldNotAllowWhitePawnVerticalAttack() {
        var pawn = new Pawn(Color.WHITE);
        board.placePiece("e2", pawn);
        board.placePiece("e3", new Pawn(Color.BLACK));

        var attack = new Capture(new Position("e2"), new Position("e3"));

        assertThrows(IlegalPlay.class, () -> attack.actUpon(board));
    }

    @Test
    void shouldNotAllowWhitePawnDiagonalDownLeftAttack() {
        var pawn = new Pawn(Color.WHITE);
        board.placePiece("e2", pawn);
        board.placePiece("d1", new Pawn(Color.BLACK));

        var attack = new Capture(new Position("e2"), new Position("d1"));

        assertThrows(IlegalPlay.class, () -> attack.actUpon(board));
    }

    @Test
    void shouldAllowQueenVerticalAttack() throws IlegalPlay {
        var queen = new Queen(Color.WHITE);
        board.placePiece("e2", queen);
        board.placePiece("e7", new Pawn(Color.BLACK));

        var attack = new Capture(new Position("e2"), new Position("e7"));
        attack.actUpon(board);

        assertNull(board.getPieceAt(new Position("e2")).orElse(null));
        assertEquals(queen, board.getPieceAt(new Position("e7")).orElseThrow());
    }

    @Test
    void shouldNowAttackAlly() throws IlegalPlay {
        var queen = new Queen(Color.WHITE);
        board.placePiece("e2", queen);
        board.placePiece("e7", new Pawn(Color.WHITE));

        var attack = new Capture(new Position("e2"), new Position("e7"));

        assertThrows(IlegalPlay.class, () -> attack.actUpon(board));
    }

    @Test
    void shouldNotAttackEmptyPositions() {
        var queen = new Queen(Color.WHITE);
        board.placePiece("e2", queen);
        var attack = new Capture(new Position("e2"), new Position("e7"));

        assertThrows(IlegalPlay.class, () -> attack.actUpon(board));
    }
}
