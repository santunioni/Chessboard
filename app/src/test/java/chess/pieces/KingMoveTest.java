package chess.pieces;

import static org.junit.jupiter.api.Assertions.assertEquals;

import chess.game.board.Board;
import chess.game.board.PlayHistory;
import chess.game.grid.Position;
import chess.game.pieces.Color;
import chess.game.pieces.King;
import chess.game.pieces.Pawn;
import chess.game.plays.Move;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class KingMoveTest {

  private Board board;
  private PlayHistory history;


  @BeforeEach
  void setUp() {
    this.board = new Board();
    this.history = new PlayHistory();
  }

  private void forwardToBlackTurn() {
    this.history.push(new Move(Color.WHITE, new Position("h7"), new Position("h8")));
  }

  @Test
  void shouldBeAbleToMoveExactlyOneSquareInAnyDirection() {
    forwardToBlackTurn();
    var king = new King(Color.BLACK);
    this.board.placePiece("d4", king);

    var expectedValidMoves = Set.of(
        new Move(Color.BLACK, new Position("d4"), new Position("c3")),
        new Move(Color.BLACK, new Position("d4"), new Position("d3")),
        new Move(Color.BLACK, new Position("d4"), new Position("e3")),

        new Move(Color.BLACK, new Position("d4"), new Position("c4")),
        new Move(Color.BLACK, new Position("d4"), new Position("e4")),

        new Move(Color.BLACK, new Position("d4"), new Position("c5")),
        new Move(Color.BLACK, new Position("d4"), new Position("d5")),
        new Move(Color.BLACK, new Position("d4"), new Position("e5"))
    );

    assertEquals(expectedValidMoves, king.getPlays(board, history));
  }

  @Test
  void shouldBeBlockedByWalls() {
    var king = new King(Color.WHITE);
    this.board.placePiece("e1", king);

    var expectedValidMoves = Set.of(
        new Move(Color.WHITE, new Position("e1"), new Position("d1")),
        new Move(Color.WHITE, new Position("e1"), new Position("f1")),

        new Move(Color.WHITE, new Position("e1"), new Position("d2")),
        new Move(Color.WHITE, new Position("e1"), new Position("e2")),
        new Move(Color.WHITE, new Position("e1"), new Position("f2"))
    );

    assertEquals(expectedValidMoves, king.getPlays(board, history));
  }

  @Test
  void shouldBeBlockedByCorner() {
    var king = new King(Color.WHITE);
    this.board.placePiece("a1", king);

    var expectedValidMoves = Set.of(
        new Move(Color.WHITE, new Position("a1"), new Position("b1")),
        new Move(Color.WHITE, new Position("a1"), new Position("a2")),
        new Move(Color.WHITE, new Position("a1"), new Position("b2"))
    );

    assertEquals(expectedValidMoves, king.getPlays(board, history));
  }

  @Test
  void shouldBeBlockedByItsTeamMates() {
    var king = new King(Color.WHITE);
    this.board.placePiece("a1", king);
    this.board.placePiece("a2", new Pawn(Color.WHITE));
    this.board.placePiece("b2", new Pawn(Color.WHITE));

    var expectedValidMoves = Set.of(
        new Move(Color.WHITE, new Position("a1"), new Position("b1"))
    );

    assertEquals(expectedValidMoves, king.getPlays(board, history));
  }
}
