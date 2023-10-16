package chess.game.grid;

import static org.junit.jupiter.api.Assertions.assertTrue;

import chess.game.board.Board;
import chess.game.board.pieces.Color;
import chess.game.board.pieces.PieceFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PathReachabilityOnOccupiedBoardTest {
  private Board board;
  private final PieceFactory pieceFactory = new PieceFactory();

  @BeforeEach
  void setUp() {
    this.board = new Board();
  }

  @Test
  void shouldNotReachA5FromA1IfPieceInA5() {
    this.board.placePiece("a5", this.pieceFactory.createPawns(Color.WHITE).get(0));
    assertTrue(new Position("a1").pathTo(new Position("a5")).orElseThrow().isBlockedOn(this.board));
  }

  @Test
  void shouldNotReachA5FromA1IfPieceInA4() {
    this.board.placePiece("a4", this.pieceFactory.createPawns(Color.WHITE).get(0));
    assertTrue(new Position("a1").pathTo(new Position("a5")).orElseThrow().isBlockedOn(this.board));
  }

  @Test
  void shouldAlwaysBeClearOnZeroSteps() {
    var path = new Path(new Position("a1"), Direction.DIAGONAL_UP_RIGHT, 0);
    assertTrue(path.isClearOn(this.board));
  }
}
