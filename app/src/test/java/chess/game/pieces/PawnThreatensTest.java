package chess.game.pieces;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import chess.game.board.Board;
import chess.game.board.pieces.Color;
import chess.game.board.pieces.Pawn;
import chess.game.grid.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PawnThreatensTest {
  private Board board;

  @BeforeEach
  void setUp() {
    this.board = new Board();
  }

  @Test
  void shouldThreatenDiagonallyUpWhenWhitePawn() {
    var pawn = new Pawn(Color.WHITE);
    this.board.placePiece("c2", pawn);

    assertTrue(pawn.couldCaptureEnemyAt(new Position("d3")));
    assertTrue(pawn.couldCaptureEnemyAt(new Position("b3")));
  }

  @Test
  void shouldThreatenDiagonallyDownWhenBlackPawn() {
    var pawn = new Pawn(Color.BLACK);
    this.board.placePiece("c7", pawn);

    assertTrue(pawn.couldCaptureEnemyAt(new Position("d6")));
    assertTrue(pawn.couldCaptureEnemyAt(new Position("b6")));
  }

  @Test
  void shouldNotThreatenDiagonallyUpWhenBlackPawn() {
    var pawn = new Pawn(Color.BLACK);
    this.board.placePiece("c2", pawn);

    assertFalse(pawn.couldCaptureEnemyAt(new Position("d3")));
    assertFalse(pawn.couldCaptureEnemyAt(new Position("b3")));
  }

  @Test
  void shouldNotThreatenDiagonallyDownWheWhitePawn() {
    var pawn = new Pawn(Color.WHITE);
    this.board.placePiece("c7", pawn);

    assertFalse(pawn.couldCaptureEnemyAt(new Position("d6")));
    assertFalse(pawn.couldCaptureEnemyAt(new Position("b6")));
  }

  @Test
  void shouldNotThreatenHorizontally() {
    var pawn = new Pawn(Color.WHITE);
    this.board.placePiece("a2", pawn);

    assertFalse(pawn.couldCaptureEnemyAt(new Position("b2")));
  }

  @Test
  void shouldNotThreatenNonDiagonalPosition() {
    var pawn = new Pawn(Color.WHITE);
    this.board.placePiece("a1", pawn);

    assertFalse(pawn.couldCaptureEnemyAt(new Position("b3")));
  }

  @Test
  void sholdNotThreatenNotNeighbor() {
    var pawn = new Pawn(Color.WHITE);
    this.board.placePiece("a1", pawn);

    assertFalse(pawn.couldCaptureEnemyAt(new Position("c3")));
  }
}
