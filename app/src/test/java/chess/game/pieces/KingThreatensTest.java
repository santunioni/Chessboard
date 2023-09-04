package chess.game.pieces;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import chess.game.board.BoardState;
import chess.game.grid.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class KingThreatensTest {
  private BoardState board;

  @BeforeEach
  void setUp() {
    this.board = new BoardState();
  }

  @Test
  public void shouldThreatenVertically() {
    var king = new King(Color.BLACK);
    board.placePiece("d4", king);

    assertTrue(king.couldCaptureEnemyAt(new Position("d5")));
  }

  @Test
  public void shouldThreatenHorizontally() {
    var king = new King(Color.BLACK);
    board.placePiece("d4", king);

    assertTrue(king.couldCaptureEnemyAt(new Position("e4")));
  }

  @Test
  public void shouldThreatenDiagonally() {
    var king = new King(Color.BLACK);
    board.placePiece("d4", king);

    assertTrue(king.couldCaptureEnemyAt(new Position("e5")));
  }

  @Test
  public void shouldNotDistantPiece() {
    var king = new King(Color.BLACK);
    board.placePiece("d4", king);

    assertFalse(king.couldCaptureEnemyAt(new Position("d6")));
  }

}
