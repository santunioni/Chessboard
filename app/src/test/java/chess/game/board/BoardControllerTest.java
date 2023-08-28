package chess.game.board;

import chess.game.grid.Position;
import chess.game.pieces.Color;
import chess.game.pieces.Piece;
import chess.game.pieces.Rook;
import chess.game.pieces.Type;
import chess.game.plays.Capture;
import chess.game.plays.IlegalPlay;
import chess.game.plays.Move;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BoardControllerTest {

    private BoardState boardState;
    private BoardController boardController;

    @BeforeEach
    void setUp() {
        this.boardState = new BoardState();
        this.boardController = new BoardController(this.boardState, new BoardHistory());
    }

    @Test
    void shouldAllowWhiteToMoveOnItsTurn() throws IlegalPlay, Exception {
        // Given
        this.boardState.placePiece("a2", new Rook(Color.BLACK));
        this.boardState.placePiece("b1", new Rook(Color.WHITE));

        // When
        var move = new Move(Color.WHITE, new Position("b1"), new Position("a1"));
        var moveDTO = move.toDTO();
        this.boardController.makePlay(moveDTO);

        // Then
        Piece pieceAtA1 = this.boardState.getPieceAt(new Position("a1")).orElseThrow();
        assertEquals(Color.WHITE, pieceAtA1.getColor());
        assertEquals(Type.ROOK, pieceAtA1.getType());
    }

    @Test
    void shouldNotAllowBlackToCaptureWhiteIfItIsNotItsTurn() {
        // Given
        this.boardState.placePiece("a2", new Rook(Color.BLACK));
        this.boardState.placePiece("a1", new Rook(Color.WHITE));

        // When
        var capture = new Capture(Color.BLACK, new Position("a2"), new Position("a1"));
        var captureDTO = capture.toDTO();

        // Then
        assertThrows(IlegalPlay.class, () -> this.boardController.makePlay(captureDTO));
    }

    @Test
    void shoudAllowBlackToCaptureWhiteOnItsTurn() throws IlegalPlay, Exception {
        // Given
        this.boardState.placePiece("a2", new Rook(Color.BLACK));
        this.boardState.placePiece("b1", new Rook(Color.WHITE));
        this.boardController.makePlay(new Move(Color.WHITE, new Position("b1"), new Position("a1")).toDTO());

        // When
        var capture = new Capture(Color.BLACK, new Position("a2"), new Position("a1"));
        var captureDTO = capture.toDTO();
        this.boardController.makePlay(captureDTO);

        // Then
        Piece pieceAtA1 = this.boardState.getPieceAt(new Position("a1")).orElseThrow();
        assertEquals(Color.BLACK, pieceAtA1.getColor());
        assertEquals(Type.ROOK, pieceAtA1.getType());
    }
}
