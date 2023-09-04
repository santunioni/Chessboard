package chess.game.pieces;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import chess.game.board.BoardState;
import chess.game.grid.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class QueenThreatensTest {

  private BoardState board;

  @BeforeEach
  void setUp() {
    this.board = new BoardState();
  }


  @Test
  public void shouldThreatenDiagonally() {
    var queen = new Queen(Color.BLACK);
    this.board.placePiece("d4", queen);

    assertTrue(queen.couldCaptureEnemyAt(new Position("f6")));
  }

  @Test
  public void shouldThreatenVertically() {
    var queen = new Queen(Color.BLACK);
    this.board.placePiece("d4", queen);

    assertTrue(queen.couldCaptureEnemyAt(new Position("d5")));
  }

  @Test
  public void shouldThreatenHorizontally() {
    var queen = new Queen(Color.BLACK);
    this.board.placePiece("d4", queen);

    assertTrue(queen.couldCaptureEnemyAt(new Position("e4")));
  }

  @Test
  public void shouldNotThreatenIfEnemyIsBetween() {
    var queen = new Queen(Color.BLACK);
    this.board.placePiece("d4", queen);
    this.board.placePiece("f6", new Pawn(Color.WHITE));

    assertFalse(queen.couldCaptureEnemyAt(new Position("g7")));
  }

  @Test
  public void shouldNotThreatenIfAllyIsBetween() {
    var queen = new Queen(Color.BLACK);
    this.board.placePiece("d4", queen);
    this.board.placePiece("f6", new Pawn(Color.BLACK));

    assertFalse(queen.couldCaptureEnemyAt(new Position("g7")));
  }
}
