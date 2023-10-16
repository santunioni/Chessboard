package chess.game.plays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import chess.game.board.Board;
import chess.game.board.pieces.Color;
import chess.game.board.pieces.Pawn;
import chess.game.board.pieces.PieceFactory;
import chess.game.grid.Position;
import chess.game.plays.validation.CapturePatternNotAllowedValidationError;
import chess.game.plays.validation.NoPieceAtPositionValidationError;
import chess.game.plays.validation.PieceAtPositionIsOfUnexpectedColorValidationError;
import chess.game.plays.validation.PlayValidationError;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CaptureTest {
  private final PieceFactory pieceFactory = new PieceFactory();
  private Board board;

  @BeforeEach
  void setUp() {
    this.board = new Board();
  }

  @Test
  void shouldAllowWhitePawnDiagonalUpLeftAttack() throws PlayValidationError {
    var pawn = new Pawn(Color.WHITE);
    board.placePiece("e2", pawn);
    board.placePiece("d3", new Pawn(Color.BLACK));

    var capture = new Capture(Color.WHITE, new Position("e2"), new Position("d3"));
    capture.actOn(board);

    assertNull(board.getPieceAt(new Position("e2")).orElse(null));
    assertEquals(pawn, board.getPieceAt(new Position("d3")).orElseThrow());
  }

  @Test
  void shouldNotAllowWhitePawnVerticalAttack() {
    var pawn = new Pawn(Color.WHITE);
    board.placePiece("e2", pawn);
    board.placePiece("e3", new Pawn(Color.BLACK));

    var capture = new Capture(Color.WHITE, new Position("e2"), new Position("e3"));

    assertThrows(CapturePatternNotAllowedValidationError.class,
        () -> capture.actOn(board));
  }

  @Test
  void shouldNotAllowWhitePawnDiagonalDownLeftAttack() {
    var pawn = new Pawn(Color.WHITE);
    board.placePiece("e2", pawn);
    board.placePiece("d1", new Pawn(Color.BLACK));

    var capture = new Capture(Color.WHITE, new Position("e2"), new Position("d1"));

    assertThrows(CapturePatternNotAllowedValidationError.class,
        () -> capture.actOn(board));
  }

  @Test
  void shouldAllowQueenVerticalAttack() throws PlayValidationError {
    var queen = this.pieceFactory.createQueen(Color.WHITE);
    board.placePiece("e2", queen);
    board.placePiece("e7", new Pawn(Color.BLACK));

    var capture = new Capture(Color.WHITE, new Position("e2"), new Position("e7"));
    capture.actOn(board);

    assertNull(board.getPieceAt(new Position("e2")).orElse(null));
    assertEquals(queen, board.getPieceAt(new Position("e7")).orElseThrow());
  }

  @Test
  void shouldNotAttackAlly() {
    var queen = this.pieceFactory.createQueen(Color.WHITE);
    board.placePiece("e2", queen);
    board.placePiece("e7", new Pawn(Color.WHITE));

    var capture = new Capture(Color.WHITE, new Position("e2"), new Position("e7"));

    assertThrows(PieceAtPositionIsOfUnexpectedColorValidationError.class,
        () -> capture.actOn(board));
  }

  @Test
  void shouldNotAttackEmptyPositions() {
    var queen = this.pieceFactory.createQueen(Color.WHITE);
    board.placePiece("e2", queen);
    var capture = new Capture(Color.WHITE, new Position("e2"), new Position("e7"));

    assertThrows(NoPieceAtPositionValidationError.class, () -> capture.actOn(board));
  }
}
