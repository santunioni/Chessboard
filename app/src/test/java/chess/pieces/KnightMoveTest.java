package chess.pieces;

import static org.junit.jupiter.api.Assertions.assertEquals;

import chess.game.board.Board;
import chess.game.board.PlayHistory;
import chess.game.grid.Position;
import chess.game.pieces.Color;
import chess.game.pieces.Knight;
import chess.game.pieces.Pawn;
import chess.game.plays.Move;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class KnightMoveTest {

  private Board board;
  private PlayHistory history;


  @BeforeEach
  void setUp() {
    this.board = new Board();
    this.history = new PlayHistory();
  }

  @Test
  void shouldMoveInL() {
    var knight = new Knight(Color.WHITE);
    this.board.placePiece("d4", knight);

    var expectedValidMoves = Set.of(
        new Move(Color.WHITE, new Position("d4"), new Position("e6")),
        new Move(Color.WHITE, new Position("d4"), new Position("c6")),

        new Move(Color.WHITE, new Position("d4"), new Position("b5")),
        new Move(Color.WHITE, new Position("d4"), new Position("b3")),

        new Move(Color.WHITE, new Position("d4"), new Position("c2")),
        new Move(Color.WHITE, new Position("d4"), new Position("e2")),

        new Move(Color.WHITE, new Position("d4"), new Position("f3")),
        new Move(Color.WHITE, new Position("d4"), new Position("f5"))
    );

    assertEquals(expectedValidMoves, knight.getPlays(board, history));
  }

  @Test
  void shouldBeBlockedByItsTeamMates() {
    var knight = new Knight(Color.WHITE);
    this.board.placePiece("b1", knight);
    this.board.placePiece("c3", new Pawn(Color.WHITE));

    var expectedValidMoves = Set.of(
        new Move(Color.WHITE, new Position("b1"), new Position("a3")),
        new Move(Color.WHITE, new Position("b1"), new Position("d2"))
    );

    assertEquals(expectedValidMoves, knight.getPlays(board, history));
  }
}
