package chess.game.board;

import chess.game.grid.Position;
import chess.game.pieces.*;
import chess.game.plays.Capture;
import chess.game.plays.Move;
import chess.game.plays.validation.NotYourTurnValidationError;
import chess.game.plays.validation.PlayValidationError;
import chess.game.rules.validation.CantLetOwnKingInCheckIlegalBoardStateError;
import chess.game.rules.validation.IlegalBoardStateError;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BoardControllerTest {

    private BoardState boardState;
    private BoardHistory boardHistory;
    private BoardController boardController;

    @BeforeEach
    void setUp() {
        this.boardState = new BoardState();
        this.boardHistory = new BoardHistory();
        this.boardController = new BoardController(this.boardState, this.boardHistory);
    }

    @Test
    void shouldAllowWhiteToMoveOnItsTurn() throws PlayValidationError, IlegalBoardStateError {
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
        assertThrows(NotYourTurnValidationError.class, () -> this.boardController.makePlay(captureDTO));
    }

    @Test
    void shoudAllowBlackToCaptureWhiteOnItsTurn() throws PlayValidationError, IlegalBoardStateError {
        // Given
        this.boardHistory.push(new Move(Color.WHITE, new Position("b1"), new Position("a1")));
        this.boardState.placePiece("a1", new Rook(Color.WHITE));
        this.boardState.placePiece("a2", new Rook(Color.BLACK));

        // When
        var capture = new Capture(Color.BLACK, new Position("a2"), new Position("a1"));
        var captureDTO = capture.toDTO();
        this.boardController.makePlay(captureDTO);

        // Then
        Piece pieceAtA1 = this.boardState.getPieceAt(new Position("a1")).orElseThrow();
        assertEquals(Color.BLACK, pieceAtA1.getColor());
        assertEquals(Type.ROOK, pieceAtA1.getType());
    }

    @Test
    void shouldNotAllowPlayerToPutItsOwnKingInCheck() {
        // Given
        this.boardState.placePiece("e1", new King(Color.WHITE));
        this.boardState.placePiece("f1", new Bishop(Color.WHITE));
        this.boardState.placePiece("h1", new Rook(Color.BLACK));

        // When
        var move = new Move(Color.WHITE, new Position("f1"), new Position("e2"));
        var moveDTO = move.toDTO();

        // Then
        assertThrows(CantLetOwnKingInCheckIlegalBoardStateError.class, () -> this.boardController.makePlay(moveDTO));
    }
}
