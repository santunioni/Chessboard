package chess.game.pieces;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import chess.game.board.Board;
import chess.game.board.pieces.Color;
import chess.game.board.pieces.Pawn;
import chess.game.board.pieces.PieceFactory;
import chess.game.grid.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BishopThreatensTest {

  private final PieceFactory pieceFactory = new PieceFactory();
  private Board board;

  @BeforeEach
  void setUp() {
    this.board = new Board();
  }


  @Test
  public void shouldThreatenDiagonally() {
    var bishop = this.pieceFactory.createBishops(Color.BLACK).get(0);
    this.board.placePiece("d4", bishop);

    assertTrue(bishop.couldCaptureEnemyAt(new Position("f6")));
  }

  @Test
  public void shouldNotThreatenIfEnemyIsBetween() {
    var bishop = this.pieceFactory.createBishops(Color.BLACK).get(0);
    this.board.placePiece("d4", bishop);
    this.board.placePiece("f6", new Pawn(Color.WHITE));

    assertFalse(bishop.couldCaptureEnemyAt(new Position("g7")));
  }

  @Test
  public void shouldNotThreatenIfAllyIsBetween() {
    var bishop = this.pieceFactory.createBishops(Color.BLACK).get(0);
    this.board.placePiece("d4", bishop);
    this.board.placePiece("f6", new Pawn(Color.BLACK));

    assertFalse(bishop.couldCaptureEnemyAt(new Position("g7")));
  }

  @Test
  public void shouldThreatenEvenIfPositionIsOccupiedByAlly() {
    var bishop = this.pieceFactory.createBishops(Color.BLACK).get(0);
    this.board.placePiece("d4", bishop);
    this.board.placePiece("f6", new Pawn(Color.BLACK));

    assertTrue(bishop.couldCaptureEnemyAt(new Position("f6")));
  }

  @Test
  public void shouldThreatenEvenIfPositionIsOccupiedByEnemy() {
    var bishop = this.pieceFactory.createBishops(Color.BLACK).get(0);
    this.board.placePiece("d4", bishop);
    this.board.placePiece("f6", new Pawn(Color.WHITE));

    assertTrue(bishop.couldCaptureEnemyAt(new Position("f6")));
  }

  @Test
  public void shouldNotThreatenVertically() {
    var bishop = this.pieceFactory.createBishops(Color.BLACK).get(0);
    this.board.placePiece("d4", bishop);

    assertFalse(bishop.couldCaptureEnemyAt(new Position("d5")));
  }

  @Test
  public void shouldNotThreatenHorizontally() {
    var bishop = this.pieceFactory.createBishops(Color.BLACK).get(0);
    this.board.placePiece("d4", bishop);

    assertFalse(bishop.couldCaptureEnemyAt(new Position("e4")));
  }
}
