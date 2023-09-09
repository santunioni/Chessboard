package chess.pieces;

import static org.junit.jupiter.api.Assertions.assertEquals;

import chess.game.board.Board;
import chess.game.board.PlayHistory;
import chess.game.grid.Position;
import chess.game.pieces.Bishop;
import chess.game.pieces.Color;
import chess.game.pieces.Pawn;
import chess.game.plays.Move;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BishopMoveTest {

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
  void shouldBeAbleToMoveDiagonally() {
    forwardToBlackTurn();
    var bishop = new Bishop(Color.BLACK);
    this.board.placePiece("d4", bishop);

    var expectedValidMoves = Set.of(
        new Move(Color.BLACK, new Position("d4"), new Position("c3")),
        new Move(Color.BLACK, new Position("d4"), new Position("b2")),
        new Move(Color.BLACK, new Position("d4"), new Position("a1")),
        new Move(Color.BLACK, new Position("d4"), new Position("c5")),
        new Move(Color.BLACK, new Position("d4"), new Position("b6")),
        new Move(Color.BLACK, new Position("d4"), new Position("a7")),
        new Move(Color.BLACK, new Position("d4"), new Position("e3")),
        new Move(Color.BLACK, new Position("d4"), new Position("f2")),
        new Move(Color.BLACK, new Position("d4"), new Position("g1")),
        new Move(Color.BLACK, new Position("d4"), new Position("e5")),
        new Move(Color.BLACK, new Position("d4"), new Position("f6")),
        new Move(Color.BLACK, new Position("d4"), new Position("g7")),
        new Move(Color.BLACK, new Position("d4"), new Position("h8"))
    );

    assertEquals(expectedValidMoves, bishop.getPlays(board, history));
  }

  @Test
  void shouldBeBlockedByItsTeamMates() {
    var bishop = new Bishop(Color.WHITE);
    this.board.placePiece("b1", bishop);
    this.board.placePiece("d3", new Pawn(Color.WHITE));

    var expectedValidMoves = Set.of(
        new Move(Color.WHITE, new Position("b1"), new Position("a2")),
        new Move(Color.WHITE, new Position("b1"), new Position("c2"))
    );

    assertEquals(expectedValidMoves, bishop.getPlays(board, history));
  }
}
