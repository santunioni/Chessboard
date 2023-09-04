package chess.game.plays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import chess.game.board.BoardHistory;
import chess.game.board.BoardState;
import chess.game.board.BoardStateFactory;
import chess.game.grid.Position;
import chess.game.pieces.Color;
import chess.game.pieces.Type;
import chess.game.plays.validation.CantEnPassantPawnThatDidntJumpLastRound;
import chess.game.plays.validation.PlayValidationError;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class EnPassantRulesTest {
  private BoardState board;
  private BoardHistory history;

  @BeforeEach
  void setUp() {
    this.board = new BoardStateFactory().createFreshBoardState();
    this.history = new BoardHistory();
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
    assertEquals(whitePawn.getType(), Type.PAWN);
    assertEquals(whitePawn.getColor(), Color.WHITE);
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
