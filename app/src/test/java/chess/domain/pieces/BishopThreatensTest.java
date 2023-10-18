package chess.domain.pieces;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import chess.domain.board.Board;
import chess.domain.grid.Position;
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

    assertTrue(bishop.threatens(new Position("f6")));
  }

  @Test
  public void shouldNotThreatenIfEnemyIsBetween() {
    var bishop = this.pieceFactory.createBishops(Color.BLACK).get(0);
    this.board.placePiece("d4", bishop);
    this.board.placePiece("f6", this.pieceFactory.createPawns(Color.WHITE).get(0));

    assertFalse(bishop.threatens(new Position("g7")));
  }

  @Test
  public void shouldNotThreatenIfAllyIsBetween() {
    var bishop = this.pieceFactory.createBishops(Color.BLACK).get(0);
    this.board.placePiece("d4", bishop);
    this.board.placePiece("f6", this.pieceFactory.createPawns(Color.BLACK).get(0));

    assertFalse(bishop.threatens(new Position("g7")));
  }

  @Test
  public void shouldThreatenEvenIfPositionIsOccupiedByAlly() {
    var bishop = this.pieceFactory.createBishops(Color.BLACK).get(0);
    this.board.placePiece("d4", bishop);
    this.board.placePiece("f6", this.pieceFactory.createPawns(Color.BLACK).get(0));

    assertTrue(bishop.threatens(new Position("f6")));
  }

  @Test
  public void shouldThreatenEvenIfPositionIsOccupiedByEnemy() {
    var bishop = this.pieceFactory.createBishops(Color.BLACK).get(0);
    this.board.placePiece("d4", bishop);
    this.board.placePiece("f6", this.pieceFactory.createPawns(Color.WHITE).get(0));

    assertTrue(bishop.threatens(new Position("f6")));
  }

  @Test
  public void shouldNotThreatenVertically() {
    var bishop = this.pieceFactory.createBishops(Color.BLACK).get(0);
    this.board.placePiece("d4", bishop);

    assertFalse(bishop.threatens(new Position("d5")));
  }

  @Test
  public void shouldNotThreatenHorizontally() {
    var bishop = this.pieceFactory.createBishops(Color.BLACK).get(0);
    this.board.placePiece("d4", bishop);

    assertFalse(bishop.threatens(new Position("e4")));
  }
}
