package chess.game.plays;

import static org.junit.jupiter.api.Assertions.assertThrows;

import chess.game.board.Board;
import chess.game.board.pieces.Color;
import chess.game.board.pieces.Pawn;
import chess.game.board.pieces.Rook;
import chess.game.grid.Position;
import chess.game.plays.validation.CantEnPassantOnInvalidRank;
import chess.game.plays.validation.CapturePatternNotAllowedValidationError;
import chess.game.plays.validation.PlayValidationError;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class EnPassantValidationTest {
  private Board board;

  @BeforeEach
  void setUp() {
    this.board = new Board();
  }

  @Test
  void shouldFailValidationWhenWhitePawnIsNotOnRankFive() {
    // Given
    board.placePiece("a2", new Pawn(Color.WHITE));

    // When
    var enPassant = new EnPassant(Color.WHITE, new Position("a2"), new Position("b3"));

    // Then
    assertThrows(CantEnPassantOnInvalidRank.class, () -> enPassant.actOn(board));
  }

  @Test
  void shouldFailValidationWhenEnPassantIsCalledOnOtherThanPawns() {
    // Given
    board.placePiece("a5", new Rook(Color.WHITE));

    // When
    var enPassant = new EnPassant(Color.WHITE, new Position("a5"), new Position("b6"));

    // Then
    assertThrows(PlayValidationError.class, () -> enPassant.actOn(board));
  }

  @Test
  void shouldFailvalidationWhenBlackPawnIsAtWhitePawnEnPassantRank() {
    // Given
    board.placePiece("a5", new Pawn(Color.BLACK));

    // When
    var enPassant = new EnPassant(Color.BLACK, new Position("a5"), new Position("b6"));

    // Then
    assertThrows(PlayValidationError.class, () -> enPassant.actOn(board));
  }

  @Test
  void shouldFailValidationOnInconsistentEnPassantPositions() {
    // Given
    board.placePiece("a5", new Pawn(Color.WHITE));

    // When
    var enPassant = new EnPassant(Color.WHITE, new Position("a5"), new Position("b7"));

    // Then
    assertThrows(CapturePatternNotAllowedValidationError.class,
        () -> enPassant.actOn(board));
  }
}
