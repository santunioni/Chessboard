package chess.game.board;

import static org.junit.jupiter.api.Assertions.assertEquals;

import chess.game.board.pieces.Color;
import chess.game.board.pieces.PieceSpecification;
import chess.game.board.pieces.PieceType;
import chess.game.grid.Position;
import chess.game.plays.Move;
import chess.game.plays.validation.PlayValidationError;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GameControllerTest {
  private GameController controller;
  private String boardId;

  @BeforeEach
  void setUp() {
    this.controller = new GameController(new BoardRepository());
    this.boardId = this.controller.newGame();
  }

  private void forwardToBlackTurn() throws PlayValidationError {
    var move = new Move(Color.WHITE, new Position("b2"), new Position("b3")).toDto();
    this.controller.makePlay(boardId, move);
  }

  @Test
  void shouldAllowWhiteToMoveOnItsTurn() throws PlayValidationError {
    // When
    var move = new Move(Color.WHITE, new Position("b2"), new Position("b3")).toDto();
    this.controller.makePlay(boardId, move);

    // Then
    var pieceAtB3 = this.controller.getPieceAt(boardId, new Position("b3")).orElseThrow();
    assertEquals(pieceAtB3, new PieceSpecification(Color.WHITE, PieceType.PAWN));
  }


  @Test
  void shouldAllowBlackToCaptureWhiteOnItsTurn() throws PlayValidationError {
    // Given
    forwardToBlackTurn();

    // When
    var move = new Move(Color.WHITE, new Position("b7"), new Position("b6")).toDto();
    this.controller.makePlay(boardId, move);

    // Then
    var pieceAtB6 = this.controller.getPieceAt(boardId, new Position("b6")).orElseThrow();
    assertEquals(pieceAtB6, new PieceSpecification(Color.BLACK, PieceType.PAWN));
  }
}
