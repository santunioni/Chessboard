package chess.domain.plays;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import chess.domain.board.Board;
import chess.domain.board.BoardInitializer;
import chess.domain.grid.Position;
import chess.domain.pieces.Color;
import chess.domain.pieces.PieceSpecification;
import chess.domain.pieces.PieceType;
import chess.domain.plays.validation.PlayValidationError;
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
    assertTrue(
        board
            .getPieceAt(new Position("b6"), new PieceSpecification(Color.WHITE, PieceType.PAWN))
            .isPresent()
    );
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
