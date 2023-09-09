package chess.game.pieces;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import chess.game.board.Board;
import chess.game.grid.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PawnMoveTest {

  private Board board;

  @BeforeEach
  void setUp() {
    this.board = new Board();
  }

  @Test
  void shouldBeAbleToMoveExactlyOneSquareUp() {
    var pawn = new Pawn(Color.WHITE);
    this.board.placePiece("a4", pawn);

    assertTrue(pawn.couldMoveToIfEmpty(new Position("a5")));
  }

  @Test
  void shouldBeAbleToMoveExactlyOneSquareDown() {
    var pawn = new Pawn(Color.BLACK);
    this.board.placePiece("b4", pawn);

    assertTrue(pawn.couldMoveToIfEmpty(new Position("b3")));
  }

  @Test
  void shouldBeAbleToMoveExactlyTwoSquaresUpIfHasNotMovedYet() {
    var pawn = new Pawn(Color.WHITE);
    this.board.placePiece("c2", pawn);

    assertTrue(pawn.couldMoveToIfEmpty(new Position("c3")));
    assertTrue(pawn.couldMoveToIfEmpty(new Position("c4")));
  }

  @Test
  void shouldBeAbleToMoveExactlyTwoSquaresDownIfHasNotMovedYet() {
    var pawn = new Pawn(Color.BLACK);
    this.board.placePiece("d7", pawn);

    assertTrue(pawn.couldMoveToIfEmpty(new Position("d6")));
    assertTrue(pawn.couldMoveToIfEmpty(new Position("d5")));
  }

  @Test
  void shouldBeBlockedByOtherPieces() {
    var pawn = new Pawn(Color.WHITE);
    this.board.placePiece("e2", pawn);
    this.board.placePiece("e3", new Pawn(Color.BLACK));

    assertTrue(pawn.couldMoveToIfEmpty(new Position("e3")));
    assertFalse(pawn.couldMoveToIfEmpty(new Position("e4")));
  }
}
