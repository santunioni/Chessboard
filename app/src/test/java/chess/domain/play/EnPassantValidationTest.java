package chess.domain.play;

import static org.junit.jupiter.api.Assertions.assertFalse;

import chess.domain.board.Board;
import chess.domain.board.PieceColor;
import chess.domain.board.PieceFactory;
import chess.domain.grid.Position;
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
    board.placePiece("a2", this.pieceFactory.createPawns(PieceColor.WHITE).get(0));

    // When
    var enPassant = new EnPassant(PieceColor.WHITE, new Position("a2"), new Position("b3"));

    // Then
    assertFalse(enPassant.canActOnCurrentState(board));
  }

  @Test
  void shouldFailValidationWhenEnPassantIsCalledOnOtherThanPawns() {
    // Given
    board.placePiece("a5", this.pieceFactory.createRooks(PieceColor.WHITE).get(0));

    // When
    var enPassant = new EnPassant(PieceColor.WHITE, new Position("a5"), new Position("b6"));

    // Then
    assertFalse(enPassant.canActOnCurrentState(board));
  }

  @Test
  void shouldFailvalidationWhenBlackPawnIsAtWhitePawnEnPassantRank() {
    // Given
    board.placePiece("a5", this.pieceFactory.createPawns(PieceColor.BLACK).get(0));

    // When
    var enPassant = new EnPassant(PieceColor.BLACK, new Position("a5"), new Position("b6"));

    // Then
    assertFalse(enPassant.canActOnCurrentState(board));
  }

  @Test
  void shouldFailValidationOnInconsistentEnPassantPositions() {
    // Given
    board.placePiece("a5", this.pieceFactory.createPawns(PieceColor.WHITE).get(0));

    // When
    var enPassant = new EnPassant(PieceColor.WHITE, new Position("a5"), new Position("b7"));

    // Then
    assertFalse(enPassant.canActOnCurrentState(board));
  }
}
