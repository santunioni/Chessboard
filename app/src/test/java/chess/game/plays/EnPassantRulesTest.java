package chess.game.plays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import chess.game.board.Board;
import chess.game.board.BoardInitializer;
import chess.game.board.PlayHistory;
import chess.game.board.pieces.Color;
import chess.game.board.pieces.PieceType;
import chess.game.grid.Position;
import chess.game.plays.validation.CantEnPassantPawnThatDidntJumpLastRound;
import chess.game.plays.validation.PlayValidationError;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class EnPassantRulesTest {
  private Board board;
  private PlayHistory history;

  @BeforeEach
  void setUp() {
    this.board = new BoardInitializer().placeAll().getBoard();
    this.history = new PlayHistory();
  }

  @Test
  void shouldCaptureBlackPieceThatRecentlyJumpedTwoSquares() throws PlayValidationError {
    // Given
    new Move(Color.WHITE, new Position("a2"), new Position("a4")).actOn(board, history);
    new Move(Color.BLACK, new Position("h7"), new Position("h5")).actOn(board, history);
    new Move(Color.WHITE, new Position("a4"), new Position("a5")).actOn(board, history);
    new Move(Color.BLACK, new Position("b7"), new Position("b5")).actOn(board, history);

    // When
    new EnPassant(Color.WHITE, new Position("a5"), new Position("b6")).actOn(board, history);

    // Then
    assertTrue(board.getPieceAt(new Position("b5")).isEmpty());
    var whitePawn = board.getPieceAt(new Position("b6")).orElseThrow();
    assertEquals(whitePawn.getSpecification().pieceType(), PieceType.PAWN);
    assertEquals(whitePawn.getSpecification().color(), Color.WHITE);
  }

  @Test
  void shouldFailIfBlackPieceHadntRecentlyMoved() throws PlayValidationError {
    // Given
    new Move(Color.WHITE, new Position("a2"), new Position("a4")).actOn(board, history);
    new Move(Color.BLACK, new Position("b7"), new Position("b5")).actOn(board, history);
    new Move(Color.WHITE, new Position("a4"), new Position("a5")).actOn(board, history);
    new Move(Color.BLACK, new Position("h7"), new Position("h5")).actOn(board, history);

    // Then
    assertThrows(CantEnPassantPawnThatDidntJumpLastRound.class,
        () -> new EnPassant(Color.WHITE, new Position("a5"), new Position("b6")).actOn(board,
            history));
  }
}
