package chess.game.plays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import chess.game.board.Board;
import chess.game.board.BoardInitializer;
import chess.game.board.pieces.Color;
import chess.game.board.pieces.PieceType;
import chess.game.grid.Position;
import chess.game.plays.validation.PlayValidationError;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class EnPassantRulesTest {
  private Board board;

  @BeforeEach
  void setUp() {
    this.board = new BoardInitializer().placeAll().getBoard();
  }

  @Test
  void shouldCaptureBlackPieceThatRecentlyJumpedTwoSquares() throws PlayValidationError {
    // Given
    board.makePlay(new Move(Color.WHITE, new Position("a2"), new Position("a4")));
    board.makePlay(new Move(Color.BLACK, new Position("h7"), new Position("h5")));
    board.makePlay(new Move(Color.WHITE, new Position("a4"), new Position("a5")));
    board.makePlay(new Move(Color.BLACK, new Position("b7"), new Position("b5")));

    // When
    board.makePlay(new EnPassant(Color.WHITE, new Position("a5"), new Position("b6")));

    // Then
    assertTrue(board.getPieceAt(new Position("b5")).isEmpty());
    var whitePawn = board.getPieceAt(new Position("b6")).orElseThrow();
    assertEquals(whitePawn.getSpecification().pieceType(), PieceType.PAWN);
    assertEquals(whitePawn.getSpecification().color(), Color.WHITE);
  }

  @Test
  void shouldFailIfBlackPieceHadNotRecentlyMoved() throws PlayValidationError {
    // Given
    board.makePlay(new Move(Color.WHITE, new Position("a2"), new Position("a4")));
    board.makePlay(new Move(Color.BLACK, new Position("b7"), new Position("b5")));
    board.makePlay(new Move(Color.WHITE, new Position("a4"), new Position("a5")));
    board.makePlay(new Move(Color.BLACK, new Position("h7"), new Position("h5")));

    // Then
    assertThrows(PlayValidationError.class,
        () -> board.makePlay(new EnPassant(Color.WHITE, new Position("a5"), new Position("b6"))));
  }
}
