package chess.pieces;

import static org.junit.jupiter.api.Assertions.assertEquals;

import chess.game.board.Board;
import chess.game.board.pieces.Color;
import chess.game.board.pieces.King;
import chess.game.board.pieces.Pawn;
import chess.game.grid.Position;
import chess.game.plays.Move;
import chess.game.plays.Play;
import com.google.common.collect.HashBiMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class KingMoveTest {

  private Board board;
  private List<Play> stack;


  @BeforeEach
  void setUp() {
    this.stack = new ArrayList<>();
    this.board = new Board(UUID.randomUUID().toString(), HashBiMap.create(), stack);
  }

  private void forwardToBlackTurn() {
    this.stack.add(new Move(Color.WHITE, new Position("h7"), new Position("h8")));
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

    assertEquals(expectedValidMoves, king.getPlays(board));
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

    assertEquals(expectedValidMoves, king.getPlays(board));
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

    assertEquals(expectedValidMoves, king.getPlays(board));
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

    assertEquals(expectedValidMoves, king.getPlays(board));
  }
}
