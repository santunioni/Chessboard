package chess.domain.play;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import chess.domain.board.Board;
import chess.domain.board.PieceColor;
import chess.domain.board.PieceFactory;
import chess.domain.board.PieceType;
import chess.domain.grid.Position;
import chess.domain.play.validation.PlayValidationError;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MoveTest {
  private final PieceFactory pieceFactory = new PieceFactory();
  private Board board;

  @BeforeEach
  void setUp() {
    this.board = new Board();
  }

  @Test
  void shouldMovePieceInBoard() throws PlayValidationError {
    var pawn = this.pieceFactory.createPawns(PieceColor.WHITE).get(0);
    board.placePieceAt("e2", pawn);

    var move = new Move(PieceType.PAWN, PieceColor.WHITE, new Position("e2"), new Position("e4"));
    board.makePlay(move);

    assertTrue(board.getPieceAt(new Position("e2")).isEmpty());
    assertEquals(pawn, board.getPieceAt(new Position("e4")).orElseThrow());
  }

  @Test
  void shouldThrownIfEmptyOriginPosition() {
    var move = new Move(PieceType.PAWN, PieceColor.WHITE, new Position("e2"), new Position("e4"));

    assertFalse(move.canActOnCurrentState(board));
  }

  @Test
  void shouldNotDisplaceToPositionOccupiedByAlly() {
    board.placePieceAt("e2", this.pieceFactory.createQueen(PieceColor.BLACK));
    board.placePieceAt("e4", this.pieceFactory.createPawns(PieceColor.BLACK).get(0));

    var move = new Move(PieceType.QUEEN, PieceColor.BLACK, new Position("e2"), new Position("e4"));

    assertFalse(move.canActOnCurrentState(board));
  }

  @Test
  void shouldNotDisplaceToPositionOccupiedByEnemy() {
    board.placePieceAt("e2", this.pieceFactory.createQueen(PieceColor.BLACK));
    board.placePieceAt("e4", this.pieceFactory.createQueen(PieceColor.WHITE));

    var move = new Move(PieceType.QUEEN, PieceColor.BLACK, new Position("e2"), new Position("e4"));

    assertFalse(move.canActOnCurrentState(board));
  }

  @Test
  void shouldReturnAlgebraicNotation() {
    var move = new Move(PieceType.QUEEN, PieceColor.BLACK, new Position("e2"), new Position("e4"));
    assertEquals("Qe2e4", move.toDto().algebraicNotation());
  }
}
