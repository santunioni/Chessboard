package chess.game.pieces;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import chess.game.board.BoardState;
import chess.game.grid.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RookThreatensTest {
  private BoardState board;

  @BeforeEach
  void setUp() {
    this.board = new BoardState();
  }

  @Test
  public void shouldThreatenVertically() {
    var rook = new Rook(Color.BLACK);
    board.placePiece("d4", rook);

    assertTrue(rook.couldCaptureEnemyAt(new Position("d5")));
  }

  @Test
  public void shouldThreatenHorizontally() {
    var rook = new Rook(Color.BLACK);
    board.placePiece("d4", rook);

    assertTrue(rook.couldCaptureEnemyAt(new Position("e4")));
  }

  @Test
  public void shouldNotThreatenIfPieceBetween() {
    var rook = new Rook(Color.BLACK);
    board.placePiece("d4", rook);
    board.placePiece("d5", new Pawn(Color.BLACK));

    assertFalse(rook.couldCaptureEnemyAt(new Position("d6")));
  }

  @Test
  public void shouldThreatenIfPieceAtPosition() {
    var rook = new Rook(Color.BLACK);
    board.placePiece("d4", rook);
    board.placePiece("d5", new Pawn(Color.BLACK));

    assertTrue(rook.couldCaptureEnemyAt(new Position("d5")));
  }
}
