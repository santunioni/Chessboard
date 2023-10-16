package chess.game.pieces;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import chess.game.board.Board;
import chess.game.board.pieces.Color;
import chess.game.board.pieces.PieceFactory;
import chess.game.grid.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PawnThreatensTest {
  private final PieceFactory pieceFactory = new PieceFactory();
  private Board board;

  @BeforeEach
  void setUp() {
    this.board = new Board();
  }

  @Test
  void shouldThreatenDiagonallyUpWhenWhitePawn() {
    var pawn = this.pieceFactory.createPawns(Color.WHITE).get(0);
    this.board.placePiece("c2", pawn);

    assertTrue(pawn.threatens(new Position("d3")));
    assertTrue(pawn.threatens(new Position("b3")));
  }

  @Test
  void shouldThreatenDiagonallyDownWhenBlackPawn() {
    var pawn = this.pieceFactory.createPawns(Color.BLACK).get(0);
    this.board.placePiece("c7", pawn);

    assertTrue(pawn.threatens(new Position("d6")));
    assertTrue(pawn.threatens(new Position("b6")));
  }

  @Test
  void shouldNotThreatenDiagonallyUpWhenBlackPawn() {
    var pawn = this.pieceFactory.createPawns(Color.BLACK).get(0);
    this.board.placePiece("c2", pawn);

    assertFalse(pawn.threatens(new Position("d3")));
    assertFalse(pawn.threatens(new Position("b3")));
  }

  @Test
  void shouldNotThreatenDiagonallyDownWheWhitePawn() {
    var pawn = this.pieceFactory.createPawns(Color.WHITE).get(0);
    this.board.placePiece("c7", pawn);

    assertFalse(pawn.threatens(new Position("d6")));
    assertFalse(pawn.threatens(new Position("b6")));
  }

  @Test
  void shouldNotThreatenHorizontally() {
    var pawn = this.pieceFactory.createPawns(Color.WHITE).get(0);
    this.board.placePiece("a2", pawn);

    assertFalse(pawn.threatens(new Position("b2")));
  }

  @Test
  void shouldNotThreatenNonDiagonalPosition() {
    var pawn = this.pieceFactory.createPawns(Color.WHITE).get(0);
    this.board.placePiece("a1", pawn);

    assertFalse(pawn.threatens(new Position("b3")));
  }

  @Test
  void shouldNotThreatenNotNeighbor() {
    var pawn = this.pieceFactory.createPawns(Color.WHITE).get(0);
    this.board.placePiece("a1", pawn);

    assertFalse(pawn.threatens(new Position("c3")));
  }
}
