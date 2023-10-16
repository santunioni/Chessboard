package chess.game.plays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import chess.game.board.Board;
import chess.game.board.pieces.Color;
import chess.game.board.pieces.PieceFactory;
import chess.game.grid.Position;
import chess.game.plays.validation.NoPieceAtPositionValidationError;
import chess.game.plays.validation.PlayValidationError;
import chess.game.plays.validation.SquareAlreadyOccupiedValidationError;
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
    move.actOn(board);

    assertNull(board.getPieceAt(new Position("e2")).orElse(null));
    assertEquals(pawn, board.getPieceAt(new Position("e4")).orElseThrow());
  }

  @Test
  void shouldThrownIfEmptyOriginPosition() {
    var move = new Move(Color.WHITE, new Position("e2"), new Position("e4"));

    assertThrows(NoPieceAtPositionValidationError.class, () -> move.actOn(board));
  }

  @Test
  void shouldNotDisplaceToPositionOccupiedByAlly() {
    board.placePiece("e2", this.pieceFactory.createQueen(Color.BLACK));
    board.placePiece("e4", this.pieceFactory.createPawns(Color.BLACK).get(0));

    var move = new Move(Color.BLACK, new Position("e2"), new Position("e4"));

    assertThrows(SquareAlreadyOccupiedValidationError.class, () -> move.actOn(board));
  }

  @Test
  void shouldNotDisplaceToPositionOccupiedByEnemy() {
    board.placePiece("e2", this.pieceFactory.createQueen(Color.BLACK));
    board.placePiece("e4", this.pieceFactory.createQueen(Color.WHITE));

    var move = new Move(Color.BLACK, new Position("e2"), new Position("e4"));

    assertThrows(SquareAlreadyOccupiedValidationError.class, () -> move.actOn(board));
  }
}
