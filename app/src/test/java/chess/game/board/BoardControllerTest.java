package chess.game.board;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import chess.game.grid.Position;
import chess.game.pieces.Bishop;
import chess.game.pieces.Color;
import chess.game.pieces.King;
import chess.game.pieces.Piece;
import chess.game.pieces.Rook;
import chess.game.pieces.Type;
import chess.game.plays.Capture;
import chess.game.plays.Move;
import chess.game.plays.validation.PlayValidationError;
import chess.game.rules.validation.CantLetOwnKingInCheck;
import chess.game.rules.validation.IlegalPlay;
import chess.game.rules.validation.NotYourTurn;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
  void shouldAllowWhiteToMoveOnItsTurn() throws PlayValidationError, IlegalPlay {
    // Given
    this.boardState.placePiece("a2", new Rook(Color.BLACK));
    this.boardState.placePiece("b1", new Rook(Color.WHITE));

    // When
    var move = new Move(Color.WHITE, new Position("b1"), new Position("a1"));
    var moveDto = move.toDto();
    this.boardController.makePlay(moveDto);

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
    var captureDto = capture.toDto();

    // Then
    assertThrows(NotYourTurn.class, () -> this.boardController.makePlay(captureDto));
  }

  @Test
  void shoudAllowBlackToCaptureWhiteOnItsTurn() throws PlayValidationError, IlegalPlay {
    // Given
    this.boardHistory.push(new Move(Color.WHITE, new Position("b1"), new Position("a1")));
    this.boardState.placePiece("a1", new Rook(Color.WHITE));
    this.boardState.placePiece("a2", new Rook(Color.BLACK));

    // When
    var capture = new Capture(Color.BLACK, new Position("a2"), new Position("a1"));
    var captureDto = capture.toDto();
    this.boardController.makePlay(captureDto);

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
    var moveDto = move.toDto();

    // Then
    assertThrows(CantLetOwnKingInCheck.class, () -> this.boardController.makePlay(moveDto));
  }
}
