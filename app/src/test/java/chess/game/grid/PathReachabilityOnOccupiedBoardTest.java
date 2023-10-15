package chess.game.grid;

import static org.junit.jupiter.api.Assertions.assertTrue;

import chess.game.board.Board;
import chess.game.board.pieces.Color;
import chess.game.board.pieces.Pawn;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PathReachabilityOnOccupiedBoardTest {
  private Board board;

  @BeforeEach
  void setUp() {
    this.board = new Board();
  }

  @Test
  void shouldNotReachA5FromA1IfPieceInA5() {
    this.board.placePiece("a5", new Pawn(Color.WHITE));
    assertTrue(new Position("a1").pathTo(new Position("a5")).orElseThrow().isBlockedOn(this.board));
  }

  @Test
  void shouldNotReachA5FromA1IfPieceInA4() {
    this.board.placePiece("a4", new Pawn(Color.WHITE));
    assertTrue(new Position("a1").pathTo(new Position("a5")).orElseThrow().isBlockedOn(this.board));
  }

  @Test
  void shouldAlwaysBeClearOnZeroSteps() {
    var path = new Path(new Position("a1"), Direction.DIAGONAL_UP_RIGHT, 0);
    assertTrue(path.isClearOn(this.board));
  }
}
