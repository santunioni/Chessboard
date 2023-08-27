package chess.game.plays;

import chess.game.board.BoardState;
import chess.game.grid.Position;
import chess.game.pieces.Color;
import chess.game.pieces.Pawn;
import chess.game.pieces.Queen;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MoveTest {
    private BoardState board;

    @BeforeEach
    void setUp() {
        this.board = new BoardState();
    }

    @Test
    void shouldMovePieceInBoard() throws IlegalPlay {
        var pawn = new Pawn(Color.WHITE);
        board.placePiece("e2", pawn);

        var displacement = new Move(new Position("e2"), new Position("e4"));
        displacement.actUpon(board);

        assertNull(board.getPieceAt(new Position("e2")).orElse(null));
        assertEquals(pawn, board.getPieceAt(new Position("e4")).orElseThrow());
    }

    @Test
    void shouldThrownIfEmptyOriginPosition() {
        var displacement = new Move(new Position("e2"), new Position("e4"));

        assertThrows(IlegalPlay.class, () -> displacement.actUpon(board));
    }

    @Test
    void shouldNotDisplaceToPositionOccupiedByAlly() {
        board.placePiece("e2", new Queen(Color.BLACK));
        board.placePiece("e4", new Queen(Color.BLACK));

        var displacement = new Move(new Position("e2"), new Position("e4"));

        assertThrows(IlegalPlay.class, () -> displacement.actUpon(board));
    }

    @Test
    void shouldNotDisplaceToPositionOccupiedByEnemy() {
        board.placePiece("e2", new Queen(Color.BLACK));
        board.placePiece("e4", new Queen(Color.WHITE));

        var displacement = new Move(new Position("e2"), new Position("e4"));

        assertThrows(IlegalPlay.class, () -> displacement.actUpon(board));
    }
}
