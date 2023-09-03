package chess.game.plays;

import chess.game.board.BoardHistory;
import chess.game.board.BoardState;
import chess.game.grid.Position;
import chess.game.pieces.Color;
import chess.game.pieces.Pawn;
import chess.game.pieces.Queen;
import chess.game.plays.validation.NoPieceAtPositionValidationError;
import chess.game.plays.validation.PlayValidationError;
import chess.game.plays.validation.SquareAlreadyOccupiedValidationError;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MoveTest {
    private BoardState board;
    private BoardHistory history;

    @BeforeEach
    void setUp() {
        this.board = new BoardState();
        this.history = new BoardHistory();
    }

    @Test
    void shouldMovePieceInBoard() throws PlayValidationError {
        var pawn = new Pawn(Color.WHITE);
        board.placePiece("e2", pawn);

        var move = new Move(Color.WHITE, new Position("e2"), new Position("e4"));
        move.actOn(board, history);

        assertNull(board.getPieceAt(new Position("e2")).orElse(null));
        assertEquals(pawn, board.getPieceAt(new Position("e4")).orElseThrow());
    }

    @Test
    void shouldThrownIfEmptyOriginPosition() {
        var move = new Move(Color.WHITE, new Position("e2"), new Position("e4"));

        assertThrows(NoPieceAtPositionValidationError.class, () -> move.actOn(board, history));
    }

    @Test
    void shouldNotDisplaceToPositionOccupiedByAlly() {
        board.placePiece("e2", new Queen(Color.BLACK));
        board.placePiece("e4", new Queen(Color.BLACK));

        var move = new Move(Color.BLACK, new Position("e2"), new Position("e4"));

        assertThrows(SquareAlreadyOccupiedValidationError.class, () -> move.actOn(board, history));
    }

    @Test
    void shouldNotDisplaceToPositionOccupiedByEnemy() {
        board.placePiece("e2", new Queen(Color.BLACK));
        board.placePiece("e4", new Queen(Color.WHITE));

        var move = new Move(Color.BLACK, new Position("e2"), new Position("e4"));

        assertThrows(SquareAlreadyOccupiedValidationError.class, () -> move.actOn(board, history));
    }
}
