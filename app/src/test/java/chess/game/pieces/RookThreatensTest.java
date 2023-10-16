package chess.game.pieces;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import chess.game.board.Board;
import chess.game.board.pieces.Color;
import chess.game.board.pieces.PieceFactory;
import chess.game.grid.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RookThreatensTest {
  private final PieceFactory pieceFactory = new PieceFactory();
  private Board board;

  @BeforeEach
  void setUp() {
    this.board = new Board();
  }

  @Test
  public void shouldThreatenVertically() {
    var rook = this.pieceFactory.createRooks(Color.BLACK).get(0);
    board.placePiece("d4", rook);

    assertTrue(rook.threatens(new Position("d5")));
  }

  @Test
  public void shouldThreatenHorizontally() {
    var rook = this.pieceFactory.createRooks(Color.BLACK).get(0);
    board.placePiece("d4", rook);

    assertTrue(rook.threatens(new Position("e4")));
  }

  @Test
  public void shouldNotThreatenIfPieceBetween() {
    var rook = this.pieceFactory.createRooks(Color.BLACK).get(0);
    board.placePiece("d4", rook);
    board.placePiece("d5", this.pieceFactory.createPawns(Color.BLACK).get(0));

    assertFalse(rook.threatens(new Position("d6")));
  }

  @Test
  public void shouldThreatenIfPieceAtPosition() {
    var rook = this.pieceFactory.createRooks(Color.BLACK).get(0);
    board.placePiece("d4", rook);
    board.placePiece("d5", this.pieceFactory.createPawns(Color.BLACK).get(0));

    assertTrue(rook.threatens(new Position("d5")));
  }
}
