package chess.game.plays;

import static org.junit.jupiter.api.Assertions.assertFalse;

import chess.game.board.Board;
import chess.game.board.pieces.Color;
import chess.game.board.pieces.PieceFactory;
import chess.game.grid.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class EnPassantValidationTest {
  private final PieceFactory pieceFactory = new PieceFactory();
  private Board board;

  @BeforeEach
  void setUp() {
    this.board = new Board();
  }

  @Test
  void shouldFailValidationWhenWhitePawnIsNotOnRankFive() {
    // Given
    board.placePiece("a2", this.pieceFactory.createPawns(Color.WHITE).get(0));

    // When
    var enPassant = new EnPassant(Color.WHITE, new Position("a2"), new Position("b3"));

    // Then
    assertFalse(enPassant.passesValidationsOn(board));
  }

  @Test
  void shouldFailValidationWhenEnPassantIsCalledOnOtherThanPawns() {
    // Given
    board.placePiece("a5", this.pieceFactory.createRooks(Color.WHITE).get(0));

    // When
    var enPassant = new EnPassant(Color.WHITE, new Position("a5"), new Position("b6"));

    // Then
    assertFalse(enPassant.passesValidationsOn(board));
  }

  @Test
  void shouldFailvalidationWhenBlackPawnIsAtWhitePawnEnPassantRank() {
    // Given
    board.placePiece("a5", this.pieceFactory.createPawns(Color.BLACK).get(0));

    // When
    var enPassant = new EnPassant(Color.BLACK, new Position("a5"), new Position("b6"));

    // Then
    assertFalse(enPassant.passesValidationsOn(board));
  }

  @Test
  void shouldFailValidationOnInconsistentEnPassantPositions() {
    // Given
    board.placePiece("a5", this.pieceFactory.createPawns(Color.WHITE).get(0));

    // When
    var enPassant = new EnPassant(Color.WHITE, new Position("a5"), new Position("b7"));

    // Then
    assertFalse(enPassant.passesValidationsOn(board));
  }
}
