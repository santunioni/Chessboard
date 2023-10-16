package chess.game.plays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import chess.game.board.Board;
import chess.game.board.pieces.Color;
import chess.game.board.pieces.King;
import chess.game.board.pieces.PieceFactory;
import chess.game.board.pieces.PieceType;
import chess.game.board.pieces.Rook;
import chess.game.grid.Position;
import chess.game.plays.validation.CantCastleOnKingThatAlreadyMoved;
import chess.game.plays.validation.CantCastleOnRookThatAlreadyMoved;
import chess.game.plays.validation.CantCastleOverOccupiedSquares;
import chess.game.plays.validation.CantCastleWhileInCheck;
import chess.game.plays.validation.CantCastleWhilePassingThroughCheck;
import chess.game.plays.validation.PlayValidationError;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CastleRulesTest {
  private final PieceFactory pieceFactory = new PieceFactory();
  private Board board;

  @BeforeEach
  void setUp() {
    this.board = new Board();
    board.placePiece("e1", this.pieceFactory.createKing(Color.WHITE));
    board.placePiece("h1", new Rook(Color.WHITE));
  }

  @Test
  void shouldMoveKingTwoSpacesRightAndPutRookOnItsLeft() throws PlayValidationError {
    // Given
    var castle = new Castle(Color.WHITE, new Position("h1"));

    // When
    board.makePlay(castle);

    // Then
    assertTrue(board.getPieceAt(King.initialPositionFor(Color.WHITE)).isEmpty());
    assertTrue(board.getPieceAt("h1").isEmpty());

    var king = board.getPieceAt(new Position("g1")).orElseThrow();
    assertEquals(king.getSpecification().pieceType(), PieceType.KING);
    assertEquals(king.getSpecification().color(), Color.WHITE);

    var rook = board.getPieceAt(new Position("f1")).orElseThrow();
    assertEquals(rook.getSpecification().pieceType(), PieceType.ROOK);
    assertEquals(rook.getSpecification().color(), Color.WHITE);
  }

  @Test
  void shouldFailWhenKingAlreadyMoved() throws PlayValidationError {
    // Given
    board.makePlay(new Move(Color.WHITE, new Position("e1"), new Position("e2")));
    board.makePlay(new Move(Color.WHITE, new Position("e2"), new Position("e1")));

    // When
    var castle = new Castle(Color.WHITE, new Position("h1"));

    // Then
    assertThrows(CantCastleOnKingThatAlreadyMoved.class, () -> board.makePlay(castle));
  }

  @Test
  void shouldFailWhenRookAlreadyMoved() throws PlayValidationError {
    // Given
    board.makePlay(new Move(Color.WHITE, new Position("h1"), new Position("h2")));
    board.makePlay(new Move(Color.WHITE, new Position("h2"), new Position("h1")));

    // When
    var castle = new Castle(Color.WHITE, new Position("h1"));

    // Then
    assertThrows(CantCastleOnRookThatAlreadyMoved.class, () -> board.makePlay(castle));
  }

  @Test
  void shouldFailIfKingIsInCheck() {
    // Given
    board.placePiece("e2", new Rook(Color.BLACK));

    // When
    var castle = new Castle(Color.WHITE, new Position("h1"));

    // Then
    assertThrows(CantCastleWhileInCheck.class, () -> board.makePlay(castle));
  }

  @Test
  void shouldFailIfPathBetweenKingAndRookIsBlocked() {
    // Given
    board.placePiece("f1", this.pieceFactory.createBishops(Color.WHITE).get(0));

    // When
    var castle = new Castle(Color.WHITE, new Position("h1"));

    // Then
    assertThrows(CantCastleOverOccupiedSquares.class, () -> board.makePlay(castle));
  }

  @Test
  void shouldFailIfKingsPathIsThreatened() {
    // Given
    board.placePiece("f2", new Rook(Color.BLACK));

    // When
    var castle = new Castle(Color.WHITE, new Position("h1"));

    // Then
    assertThrows(CantCastleWhilePassingThroughCheck.class, () -> board.makePlay(castle));
  }
}
