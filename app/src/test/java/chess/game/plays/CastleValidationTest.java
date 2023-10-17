package chess.game.plays;

import static org.junit.jupiter.api.Assertions.assertFalse;

import chess.game.board.Board;
import chess.game.board.pieces.Color;
import chess.game.board.pieces.PieceFactory;
import chess.game.grid.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CastleValidationTest {
  private final PieceFactory pieceFactory = new PieceFactory();
  private Board board;

  @BeforeEach
  void setUp() {
    this.board = new Board();
  }

  @Test
  void shouldFailValidationWhenKingNotInPosition() {
    // Given
    board.placePiece("e2", this.pieceFactory.createKing(Color.WHITE));
    board.placePiece("h1", this.pieceFactory.createRooks(Color.WHITE).get(0));

    // When
    var castle = new Castle(Color.WHITE, new Position("h1"));

    // Then
    assertFalse(castle.passesValidationsOn(board));
  }

  @Test
  void shouldFailValidationWhenRookNotInPosition() {
    // Given
    board.placePiece("e1", this.pieceFactory.createKing(Color.WHITE));
    board.placePiece("h2", this.pieceFactory.createRooks(Color.WHITE).get(0));

    // When
    var castle = new Castle(Color.WHITE, new Position("h1"));

    // Then
    assertFalse(castle.passesValidationsOn(board));
  }

  @Test
  void shouldFailValidationWhenCastlingToPositionOtherThanRooks() {
    // Given
    board.placePiece("e1", this.pieceFactory.createKing(Color.WHITE));
    board.placePiece("h2", this.pieceFactory.createRooks(Color.WHITE).get(0));

    // When
    var castle = new Castle(Color.WHITE, new Position("h2"));

    // Then
    assertFalse(castle.passesValidationsOn(board));
  }
}
