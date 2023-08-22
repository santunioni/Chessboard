package chess.plays;

import chess.board.BoardState;
import chess.board.position.Position;
import chess.pieces.Color;
import chess.pieces.Pawn;
import chess.pieces.Queen;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DisplacementTest {
    private BoardState board;

    @BeforeEach
    void setUp() {
        this.board = new BoardState();
    }

    @Test
    void shouldMovePieceInBoard() throws IlegalPlay {
        var pawn = new Pawn(Color.WHITE);
        board.placePiece("e2", pawn);

        var displacement = new Displacement("e2", "e4");
        displacement.actUpon(board);

        assertNull(board.getPieceAt(new Position("e2")).orElse(null));
        assertEquals(pawn, board.getPieceAt(new Position("e4")).orElseThrow());
    }

    @Test
    void shouldThrownIfEmptyOriginPosition() {
        var displacement = new Displacement("e2", "e4");

        assertThrows(IlegalPlay.class, () -> displacement.actUpon(board));
    }

    @Test
    void shouldNotDisplaceToPositionOccupiedByAlly() {
        board.placePiece("e2", new Queen(Color.BLACK));
        board.placePiece("e4", new Queen(Color.BLACK));

        var displacement = new Displacement("e2", "e4");

        assertThrows(IlegalPlay.class, () -> displacement.actUpon(board));
    }

    @Test
    void shouldNotDisplaceToPositionOccupiedByEnemy() {
        board.placePiece("e2", new Queen(Color.BLACK));
        board.placePiece("e4", new Queen(Color.WHITE));

        var displacement = new Displacement("e2", "e4");

        assertThrows(IlegalPlay.class, () -> displacement.actUpon(board));
    }
}
