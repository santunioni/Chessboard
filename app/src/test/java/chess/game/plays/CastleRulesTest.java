package chess.game.plays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import chess.game.board.Board;
import chess.game.board.PlayHistory;
import chess.game.grid.Position;
import chess.game.pieces.Bishop;
import chess.game.pieces.Color;
import chess.game.pieces.King;
import chess.game.pieces.Rook;
import chess.game.pieces.Type;
import chess.game.plays.validation.CantCastleOnKingThatAlreadyMoved;
import chess.game.plays.validation.CantCastleOnRookThatAlreadyMoved;
import chess.game.plays.validation.CantCastleOverOccupiedSquares;
import chess.game.plays.validation.CantCastleWhileInCheck;
import chess.game.plays.validation.CantCastleWhilePassingThroughCheck;
import chess.game.plays.validation.PlayValidationError;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CastleRulesTest {
  private Board board;
  private PlayHistory history;

  @BeforeEach
  void setUp() {
    this.board = new Board();
    this.history = new PlayHistory();
    board.placePiece("e1", new King(Color.WHITE));
    board.placePiece("h1", new Rook(Color.WHITE));
  }

  @Test
  void shouldMoveKingTwoSpacesRightAndPutRookOnItsLeft() throws PlayValidationError {
    // Given
    var castle = new Castle(Color.WHITE, new Position("h1"));

    // When
    castle.actOn(board, history);

    // Then
    assertTrue(board.getPieceAt(King.initialPosition(Color.WHITE)).isEmpty());
    assertTrue(board.getPieceAt("h1").isEmpty());

    var king = board.getPieceAt(new Position("g1")).orElseThrow();
    assertEquals(king.getType(), Type.KING);
    assertEquals(king.getColor(), Color.WHITE);

    var rook = board.getPieceAt(new Position("f1")).orElseThrow();
    assertEquals(rook.getType(), Type.ROOK);
    assertEquals(rook.getColor(), Color.WHITE);
  }

  @Test
  void shouldFailWhenKingAlreadyMoved() throws PlayValidationError {
    // Given
    new Move(Color.WHITE, new Position("e1"), new Position("e2")).actOn(board, history);
    new Move(Color.WHITE, new Position("e2"), new Position("e1")).actOn(board, history);

    // When
    var castle = new Castle(Color.WHITE, new Position("h1"));

    // Then
    assertThrows(CantCastleOnKingThatAlreadyMoved.class, () -> castle.actOn(board, history));
  }

  @Test
  void shouldFailWhenRookAlreadyMoved() throws PlayValidationError {
    // Given
    new Move(Color.WHITE, new Position("h1"), new Position("h2")).actOn(board, history);
    new Move(Color.WHITE, new Position("h2"), new Position("h1")).actOn(board, history);

    // When
    var castle = new Castle(Color.WHITE, new Position("h1"));

    // Then
    assertThrows(CantCastleOnRookThatAlreadyMoved.class, () -> castle.actOn(board, history));
  }

  @Test
  void shouldFailIfKingIsInCheck() {
    // Given
    board.placePiece("e2", new Rook(Color.BLACK));

    // When
    var castle = new Castle(Color.WHITE, new Position("h1"));

    // Then
    assertThrows(CantCastleWhileInCheck.class, () -> castle.actOn(board, history));
  }

  @Test
  void shouldFailIfPathBetweenKingAndRookIsBlocked() {
    // Given
    board.placePiece("f1", new Bishop(Color.WHITE));

    // When
    var castle = new Castle(Color.WHITE, new Position("h1"));

    // Then
    assertThrows(CantCastleOverOccupiedSquares.class, () -> castle.actOn(board, history));
  }

  @Test
  void shouldFailIfKingsPathIsThreatened() {
    // Given
    board.placePiece("f2", new Rook(Color.BLACK));

    // When
    var castle = new Castle(Color.WHITE, new Position("h1"));

    // Then
    assertThrows(CantCastleWhilePassingThroughCheck.class, () -> castle.actOn(board, history));
  }
}
