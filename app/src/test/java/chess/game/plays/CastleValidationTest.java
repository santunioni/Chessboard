package chess.game.plays;

import static org.junit.jupiter.api.Assertions.assertThrows;

import chess.game.board.Board;
import chess.game.board.PlayHistory;
import chess.game.board.pieces.Color;
import chess.game.board.pieces.King;
import chess.game.board.pieces.Rook;
import chess.game.grid.Position;
import chess.game.plays.validation.CantCastleOnKingThatAlreadyMoved;
import chess.game.plays.validation.CantCastleOnRookThatAlreadyMoved;
import chess.game.plays.validation.CantCastleToInvalidPosition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CastleValidationTest {
  private Board board;
  private PlayHistory history;

  @BeforeEach
  void setUp() {
    this.board = new Board();
    this.history = new PlayHistory();
  }

  @Test
  void shouldFailValidationWhenKingNotInPosition() {
    // Given
    board.placePiece("e2", new King(Color.WHITE));
    board.placePiece("h1", new Rook(Color.WHITE));

    // When
    var castle = new Castle(Color.WHITE, new Position("h1"));

    // Then
    assertThrows(CantCastleOnKingThatAlreadyMoved.class, () -> castle.actOn(board, history));
  }

  @Test
  void shouldFailValidationWhenRookNotInPosition() {
    // Given
    board.placePiece("e1", new King(Color.WHITE));
    board.placePiece("h2", new Rook(Color.WHITE));

    // When
    var castle = new Castle(Color.WHITE, new Position("h1"));

    // Then
    assertThrows(CantCastleOnRookThatAlreadyMoved.class, () -> castle.actOn(board, history));
  }

  @Test
  void shouldFailValidationWhenCastlingToPositionOtherThanRooks() {
    // Given
    board.placePiece("e1", new King(Color.WHITE));
    board.placePiece("h2", new Rook(Color.WHITE));

    // When
    var castle = new Castle(Color.WHITE, new Position("h2"));

    // Then
    assertThrows(CantCastleToInvalidPosition.class, () -> castle.actOn(board, history));
  }
}
