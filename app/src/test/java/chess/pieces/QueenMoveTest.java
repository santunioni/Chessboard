package chess.pieces;

import static org.junit.jupiter.api.Assertions.assertEquals;

import chess.game.board.Board;
import chess.game.board.PlayHistory;
import chess.game.board.pieces.Color;
import chess.game.board.pieces.Pawn;
import chess.game.board.pieces.Queen;
import chess.game.grid.Position;
import chess.game.plays.Move;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class QueenMoveTest {

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
  public void shouldBeAbleToMoveHorizontalyVerticallyAndDiagonaly() {
    forwardToBlackTurn();
    var queen = new Queen(Color.BLACK);
    this.board.placePiece("d4", queen);

    var expectedValidMoves = Set.of(
        new Move(Color.BLACK, new Position("d4"), new Position("a1")),
        new Move(Color.BLACK, new Position("d4"), new Position("b2")),
        new Move(Color.BLACK, new Position("d4"), new Position("c3")),
        new Move(Color.BLACK, new Position("d4"), new Position("e5")),
        new Move(Color.BLACK, new Position("d4"), new Position("f6")),
        new Move(Color.BLACK, new Position("d4"), new Position("g7")),
        new Move(Color.BLACK, new Position("d4"), new Position("h8")),

        new Move(Color.BLACK, new Position("d4"), new Position("a7")),
        new Move(Color.BLACK, new Position("d4"), new Position("b6")),
        new Move(Color.BLACK, new Position("d4"), new Position("c5")),
        new Move(Color.BLACK, new Position("d4"), new Position("e3")),
        new Move(Color.BLACK, new Position("d4"), new Position("f2")),
        new Move(Color.BLACK, new Position("d4"), new Position("g1")),

        new Move(Color.BLACK, new Position("d4"), new Position("d1")),
        new Move(Color.BLACK, new Position("d4"), new Position("d2")),
        new Move(Color.BLACK, new Position("d4"), new Position("d3")),
        new Move(Color.BLACK, new Position("d4"), new Position("d5")),
        new Move(Color.BLACK, new Position("d4"), new Position("d6")),
        new Move(Color.BLACK, new Position("d4"), new Position("d7")),
        new Move(Color.BLACK, new Position("d4"), new Position("d8")),

        new Move(Color.BLACK, new Position("d4"), new Position("a4")),
        new Move(Color.BLACK, new Position("d4"), new Position("b4")),
        new Move(Color.BLACK, new Position("d4"), new Position("c4")),
        new Move(Color.BLACK, new Position("d4"), new Position("e4")),
        new Move(Color.BLACK, new Position("d4"), new Position("f4")),
        new Move(Color.BLACK, new Position("d4"), new Position("g4")),
        new Move(Color.BLACK, new Position("d4"), new Position("h4"))
    );

    assertEquals(expectedValidMoves, queen.getPlays(board, history));
  }

  @Test
  void shouldBeBlockedByItsTeamMates() {
    var queen = new Queen(Color.WHITE);
    this.board.placePiece("a1", queen);
    this.board.placePiece("e1", new Pawn(Color.WHITE));
    this.board.placePiece("a2", new Pawn(Color.WHITE));
    this.board.placePiece("b2", new Pawn(Color.WHITE));

    var expectedValidMoves = Set.of(
        new Move(Color.WHITE, new Position("a1"), new Position("b1")),
        new Move(Color.WHITE, new Position("a1"), new Position("c1")),
        new Move(Color.WHITE, new Position("a1"), new Position("d1"))
    );

    assertEquals(expectedValidMoves, queen.getPlays(board, history));
  }
}
