package chess.domain.play;

import static org.junit.jupiter.api.Assertions.assertFalse;

import chess.domain.board.Board;
import chess.domain.board.PieceColor;
import chess.domain.board.PieceFactory;
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
    board.placePiece("e2", this.pieceFactory.createKing(PieceColor.WHITE));
    board.placePiece("h1", this.pieceFactory.createRooks(PieceColor.WHITE).get(0));

    // When
    var castle = new Castle(PieceColor.WHITE, CastleSide.KING_SIDE);

    // Then
    assertFalse(castle.canActOnCurrentState(board));
  }

  @Test
  void shouldFailValidationWhenRookNotInPosition() {
    // Given
    board.placePiece("e1", this.pieceFactory.createKing(PieceColor.WHITE));
    board.placePiece("h2", this.pieceFactory.createRooks(PieceColor.WHITE).get(0));

    // When
    var castle = new Castle(PieceColor.WHITE, CastleSide.KING_SIDE);

    // Then
    assertFalse(castle.canActOnCurrentState(board));
  }

  @Test
  void shouldFailValidationWhenCastlingToPositionOtherThanRooks() {
    // Given
    board.placePiece("e1", this.pieceFactory.createKing(PieceColor.WHITE));
    board.placePiece("h2", this.pieceFactory.createRooks(PieceColor.WHITE).get(0));

    // When
    var castle = new Castle(PieceColor.WHITE, CastleSide.QUEEN_SIDE);

    // Then
    assertFalse(castle.canActOnCurrentState(board));
  }
}
