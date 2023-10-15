package chess.game.board;

import static org.junit.jupiter.api.Assertions.assertEquals;

import chess.game.board.pieces.Color;
import chess.game.board.pieces.Piece;
import chess.game.board.pieces.PieceType;
import chess.game.board.pieces.Rook;
import chess.game.grid.Position;
import chess.game.plays.Capture;
import chess.game.plays.Move;
import chess.game.plays.validation.PlayValidationError;
import chess.game.rules.validation.IlegalPlay;
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
    assertEquals(Color.WHITE, pieceAtA1.getSpecification().color());
    assertEquals(PieceType.ROOK, pieceAtA1.getSpecification().pieceType());
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
    assertEquals(Color.BLACK, pieceAtA1.getSpecification().color());
    assertEquals(PieceType.ROOK, pieceAtA1.getSpecification().pieceType());
  }
}
