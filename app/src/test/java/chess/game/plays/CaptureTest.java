package chess.game.plays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;

import chess.game.board.Board;
import chess.game.board.pieces.Color;
import chess.game.board.pieces.PieceFactory;
import chess.game.grid.Position;
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
    var pawn = this.pieceFactory.createPawns(Color.WHITE).get(0);
    board.placePiece("e2", pawn);
    board.placePiece("d3", this.pieceFactory.createPawns(Color.BLACK).get(0));

    var capture = new Capture(Color.WHITE, new Position("e2"), new Position("d3"));
    capture.actOn(board);

    assertNull(board.getPieceAt(new Position("e2")).orElse(null));
    assertEquals(pawn, board.getPieceAt(new Position("d3")).orElseThrow());
  }

  @Test
  void shouldNotAllowWhitePawnVerticalAttack() {
    var pawn = this.pieceFactory.createPawns(Color.WHITE).get(0);
    board.placePiece("e2", pawn);
    board.placePiece("e3", this.pieceFactory.createPawns(Color.BLACK).get(0));

    var capture = new Capture(Color.WHITE, new Position("e2"), new Position("e3"));

    assertFalse(capture.passesValidationsOn(board));
  }

  @Test
  void shouldNotAllowWhitePawnDiagonalDownLeftAttack() {
    var pawn = this.pieceFactory.createPawns(Color.WHITE).get(0);
    board.placePiece("e2", pawn);
    board.placePiece("d1", this.pieceFactory.createPawns(Color.BLACK).get(0));

    var capture = new Capture(Color.WHITE, new Position("e2"), new Position("d1"));

    assertFalse(capture.passesValidationsOn(board));
  }

  @Test
  void shouldAllowQueenVerticalAttack() throws PlayValidationError {
    var queen = this.pieceFactory.createQueen(Color.WHITE);
    board.placePiece("e2", queen);
    board.placePiece("e7", this.pieceFactory.createPawns(Color.BLACK).get(0));

    var capture = new Capture(Color.WHITE, new Position("e2"), new Position("e7"));
    capture.actOn(board);

    assertNull(board.getPieceAt(new Position("e2")).orElse(null));
    assertEquals(queen, board.getPieceAt(new Position("e7")).orElseThrow());
  }

  @Test
  void shouldNotAttackAlly() {
    var queen = this.pieceFactory.createQueen(Color.WHITE);
    board.placePiece("e2", queen);
    board.placePiece("e7", this.pieceFactory.createPawns(Color.WHITE).get(0));

    var capture = new Capture(Color.WHITE, new Position("e2"), new Position("e7"));

    assertFalse(capture.passesValidationsOn(board));
  }

  @Test
  void shouldNotAttackEmptyPositions() {
    var queen = this.pieceFactory.createQueen(Color.WHITE);
    board.placePiece("e2", queen);
    var capture = new Capture(Color.WHITE, new Position("e2"), new Position("e7"));

    assertFalse(capture.passesValidationsOn(board));
  }
}
