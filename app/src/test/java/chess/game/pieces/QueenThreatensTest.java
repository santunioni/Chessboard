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

public class QueenThreatensTest {

  private final PieceFactory pieceFactory = new PieceFactory();
  private Board board;

  @BeforeEach
  void setUp() {
    this.board = new Board();
  }


  @Test
  public void shouldThreatenDiagonally() {
    var queen = this.pieceFactory.createQueen(Color.BLACK);
    this.board.placePiece("d4", queen);

    assertTrue(queen.couldCaptureEnemyAt(new Position("f6")));
  }

  @Test
  public void shouldThreatenVertically() {
    var queen = this.pieceFactory.createQueen(Color.BLACK);
    this.board.placePiece("d4", queen);

    assertTrue(queen.couldCaptureEnemyAt(new Position("d5")));
  }

  @Test
  public void shouldThreatenHorizontally() {
    var queen = this.pieceFactory.createQueen(Color.BLACK);
    this.board.placePiece("d4", queen);

    assertTrue(queen.couldCaptureEnemyAt(new Position("e4")));
  }

  @Test
  public void shouldNotThreatenIfEnemyIsBetween() {
    var queen = this.pieceFactory.createQueen(Color.BLACK);
    this.board.placePiece("d4", queen);
    this.board.placePiece("f6", new Pawn(Color.WHITE));

    assertFalse(queen.couldCaptureEnemyAt(new Position("g7")));
  }

  @Test
  public void shouldNotThreatenIfAllyIsBetween() {
    var queen = this.pieceFactory.createQueen(Color.BLACK);
    this.board.placePiece("d4", queen);
    this.board.placePiece("f6", new Pawn(Color.BLACK));

    assertFalse(queen.couldCaptureEnemyAt(new Position("g7")));
  }
}
