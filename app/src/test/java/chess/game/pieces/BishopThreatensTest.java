package chess.game.pieces;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import chess.game.board.Board;
import chess.game.board.pieces.Bishop;
import chess.game.board.pieces.Color;
import chess.game.board.pieces.Pawn;
import chess.game.grid.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BishopThreatensTest {

  private Board board;

  @BeforeEach
  void setUp() {
    this.board = new Board();
  }


  @Test
  public void shouldThreatenDiagonally() {
    var bishop = new Bishop(Color.BLACK);
    this.board.placePiece("d4", bishop);

    assertTrue(bishop.couldCaptureEnemyAt(new Position("f6")));
  }

  @Test
  public void shouldNotThreatenIfEnemyIsBetween() {
    var bishop = new Bishop(Color.BLACK);
    this.board.placePiece("d4", bishop);
    this.board.placePiece("f6", new Pawn(Color.WHITE));

    assertFalse(bishop.couldCaptureEnemyAt(new Position("g7")));
  }

  @Test
  public void shouldNotThreatenIfAllyIsBetween() {
    var bishop = new Bishop(Color.BLACK);
    this.board.placePiece("d4", bishop);
    this.board.placePiece("f6", new Pawn(Color.BLACK));

    assertFalse(bishop.couldCaptureEnemyAt(new Position("g7")));
  }

  @Test
  public void shouldThreatenEvenIfPositionIsOccupiedByAlly() {
    var bishop = new Bishop(Color.BLACK);
    this.board.placePiece("d4", bishop);
    this.board.placePiece("f6", new Pawn(Color.BLACK));

    assertTrue(bishop.couldCaptureEnemyAt(new Position("f6")));
  }

  @Test
  public void shouldThreatenEvenIfPositionIsOccupiedByEnemy() {
    var bishop = new Bishop(Color.BLACK);
    this.board.placePiece("d4", bishop);
    this.board.placePiece("f6", new Pawn(Color.WHITE));

    assertTrue(bishop.couldCaptureEnemyAt(new Position("f6")));
  }

  @Test
  public void shouldNotThreatenVertically() {
    var bishop = new Bishop(Color.BLACK);
    this.board.placePiece("d4", bishop);

    assertFalse(bishop.couldCaptureEnemyAt(new Position("d5")));
  }

  @Test
  public void shouldNotThreatenHorizontally() {
    var bishop = new Bishop(Color.BLACK);
    this.board.placePiece("d4", bishop);

    assertFalse(bishop.couldCaptureEnemyAt(new Position("e4")));
  }
}
