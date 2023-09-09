package chess.game.plays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import chess.game.board.Board;
import chess.game.board.PlayHistory;
import chess.game.grid.Position;
import chess.game.pieces.Color;
import chess.game.pieces.Pawn;
import chess.game.pieces.Queen;
import chess.game.plays.validation.NoPieceAtPositionValidationError;
import chess.game.plays.validation.PlayValidationError;
import chess.game.plays.validation.SquareAlreadyOccupiedValidationError;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MoveTest {
  private Board board;
  private PlayHistory history;

  @BeforeEach
  void setUp() {
    this.board = new Board();
    this.history = new PlayHistory();
  }

  @Test
  void shouldMovePieceInBoard() throws PlayValidationError {
    var pawn = new Pawn(Color.WHITE);
    board.placePiece("e2", pawn);

    var move = new Move(Color.WHITE, new Position("e2"), new Position("e4"));
    move.actOn(board, history);

    assertNull(board.getPieceAt(new Position("e2")).orElse(null));
    assertEquals(pawn, board.getPieceAt(new Position("e4")).orElseThrow());
  }

  @Test
  void shouldThrownIfEmptyOriginPosition() {
    var move = new Move(Color.WHITE, new Position("e2"), new Position("e4"));

    assertThrows(NoPieceAtPositionValidationError.class, () -> move.actOn(board, history));
  }

  @Test
  void shouldNotDisplaceToPositionOccupiedByAlly() {
    board.placePiece("e2", new Queen(Color.BLACK));
    board.placePiece("e4", new Queen(Color.BLACK));

    var move = new Move(Color.BLACK, new Position("e2"), new Position("e4"));

    assertThrows(SquareAlreadyOccupiedValidationError.class, () -> move.actOn(board, history));
  }

  @Test
  void shouldNotDisplaceToPositionOccupiedByEnemy() {
    board.placePiece("e2", new Queen(Color.BLACK));
    board.placePiece("e4", new Queen(Color.WHITE));

    var move = new Move(Color.BLACK, new Position("e2"), new Position("e4"));

    assertThrows(SquareAlreadyOccupiedValidationError.class, () -> move.actOn(board, history));
  }
}
