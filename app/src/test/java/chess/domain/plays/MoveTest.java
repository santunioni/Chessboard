package chess.domain.plays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import chess.domain.board.Board;
import chess.domain.grid.Position;
import chess.domain.pieces.Color;
import chess.domain.pieces.PieceFactory;
import chess.domain.plays.validation.PlayValidationError;
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
    var pawn = this.pieceFactory.createPawns(Color.WHITE).get(0);
    board.placePiece("e2", pawn);

    var move = new Move(Color.WHITE, new Position("e2"), new Position("e4"));
    board.makePlay(move);

    assertTrue(board.getPieceAt(new Position("e2")).isEmpty());
    assertEquals(pawn, board.getPieceAt(new Position("e4")).orElseThrow());
  }

  @Test
  void shouldThrownIfEmptyOriginPosition() {
    var move = new Move(Color.WHITE, new Position("e2"), new Position("e4"));

    assertFalse(move.isLegalOn(board));
  }

  @Test
  void shouldNotDisplaceToPositionOccupiedByAlly() {
    board.placePiece("e2", this.pieceFactory.createQueen(Color.BLACK));
    board.placePiece("e4", this.pieceFactory.createPawns(Color.BLACK).get(0));

    var move = new Move(Color.BLACK, new Position("e2"), new Position("e4"));

    assertFalse(move.isLegalOn(board));
  }

  @Test
  void shouldNotDisplaceToPositionOccupiedByEnemy() {
    board.placePiece("e2", this.pieceFactory.createQueen(Color.BLACK));
    board.placePiece("e4", this.pieceFactory.createQueen(Color.WHITE));

    var move = new Move(Color.BLACK, new Position("e2"), new Position("e4"));

    assertFalse(move.isLegalOn(board));
  }
}
