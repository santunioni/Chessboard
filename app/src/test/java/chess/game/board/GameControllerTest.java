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

public class GameControllerTest {

  private Board board;
  private PlayHistory history;
  private GameController controller;

  @BeforeEach
  void setUp() {
    this.board = new Board();
    this.history = new PlayHistory();
    this.controller = new GameController(this.board, this.history);
  }

  @Test
  void shouldAllowWhiteToMoveOnItsTurn() throws PlayValidationError, IlegalPlay {
    // Given
    this.board.placePiece("a2", new Rook(Color.BLACK));
    this.board.placePiece("b1", new Rook(Color.WHITE));

    // When
    var move = new Move(Color.WHITE, new Position("b1"), new Position("a1"));
    var moveDto = move.toDto();
    this.controller.makePlay(moveDto);

    // Then
    Piece pieceAtA1 = this.board.getPieceAt(new Position("a1")).orElseThrow();
    assertEquals(Color.WHITE, pieceAtA1.getColor());
    assertEquals(Type.ROOK, pieceAtA1.getType());
  }

  @Test
  void shouldNotAllowBlackToCaptureWhiteIfItIsNotItsTurn() {
    // Given
    this.board.placePiece("a2", new Rook(Color.BLACK));
    this.board.placePiece("a1", new Rook(Color.WHITE));

    // When
    var capture = new Capture(Color.BLACK, new Position("a2"), new Position("a1"));
    var captureDto = capture.toDto();

    // Then
    assertThrows(NotYourTurn.class, () -> this.controller.makePlay(captureDto));
  }

  @Test
  void shoudAllowBlackToCaptureWhiteOnItsTurn() throws PlayValidationError, IlegalPlay {
    // Given
    this.history.push(new Move(Color.WHITE, new Position("b1"), new Position("a1")));
    this.board.placePiece("a1", new Rook(Color.WHITE));
    this.board.placePiece("a2", new Rook(Color.BLACK));

    // When
    var capture = new Capture(Color.BLACK, new Position("a2"), new Position("a1"));
    var captureDto = capture.toDto();
    this.controller.makePlay(captureDto);

    // Then
    Piece pieceAtA1 = this.board.getPieceAt(new Position("a1")).orElseThrow();
    assertEquals(Color.BLACK, pieceAtA1.getColor());
    assertEquals(Type.ROOK, pieceAtA1.getType());
  }

  @Test
  void shouldNotAllowPlayerToPutItsOwnKingInCheck() {
    // Given
    this.board.placePiece("e1", new King(Color.WHITE));
    this.board.placePiece("f1", new Bishop(Color.WHITE));
    this.board.placePiece("h1", new Rook(Color.BLACK));

    // When
    var move = new Move(Color.WHITE, new Position("f1"), new Position("e2"));
    var moveDto = move.toDto();

    // Then
    assertThrows(CantLetOwnKingInCheck.class, () -> this.controller.makePlay(moveDto));
  }
}
