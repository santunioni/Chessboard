package chess.application;

import static org.junit.jupiter.api.Assertions.assertEquals;

import chess.domain.board.BoardRepository;
import chess.domain.board.PieceColor;
import chess.domain.board.PieceSpecification;
import chess.domain.board.PieceType;
import chess.domain.grid.Position;
import chess.domain.play.Move;
import chess.domain.play.validation.PlayValidationError;
import java.util.Set;
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
    var move =
        new Move(PieceType.PAWN, PieceColor.WHITE, new Position("b2"), new Position("b3")).toDto();
    this.controller.makePlay(boardId, move);
  }

  @Test
  void shouldAllowWhiteToMoveOnItsTurn() throws PlayValidationError {
    // When
    var move =
        new Move(PieceType.PAWN, PieceColor.WHITE, new Position("b2"), new Position("b3")).toDto();
    this.controller.makePlay(boardId, move);

    // Then
    var pieceAtB3 = this.controller.getPieceAt(boardId, new Position("b3")).orElseThrow();
    assertEquals(pieceAtB3, new PieceSpecification(PieceColor.WHITE, PieceType.PAWN));
  }


  @Test
  void shouldAllowBlackToMoveOnItsTurn() throws PlayValidationError {
    // Given
    forwardToBlackTurn();

    // When
    var move =
        new Move(PieceType.PAWN, PieceColor.BLACK, new Position("b7"), new Position("b6")).toDto();
    this.controller.makePlay(boardId, move);

    // Then
    var pieceAtB6 = this.controller.getPieceAt(boardId, new Position("b6")).orElseThrow();
    assertEquals(pieceAtB6, new PieceSpecification(PieceColor.BLACK, PieceType.PAWN));
  }

  @Test
  void shouldSuggestWhitePawnToMoveOneOrTwoSquares() {
    // When
    var plays = this.controller.getPlaysFor(boardId, new Position("b2"));

    // Then
    assertEquals(Set.of(
            new Move(PieceType.PAWN, PieceColor.WHITE,
                new Position("b2"), new Position("b3")).toDto(),
            new Move(PieceType.PAWN, PieceColor.WHITE,
                new Position("b2"), new Position("b4")).toDto()),
        plays);
  }
}
