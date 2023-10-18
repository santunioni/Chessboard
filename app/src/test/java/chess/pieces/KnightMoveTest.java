package chess.pieces;

import static org.junit.jupiter.api.Assertions.assertEquals;

import chess.domain.board.Board;
import chess.domain.grid.Position;
import chess.domain.pieces.Color;
import chess.domain.pieces.PieceFactory;
import chess.domain.plays.Move;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class KnightMoveTest {

  private final PieceFactory pieceFactory = new PieceFactory();
  private Board board;

  @BeforeEach
  void setUp() {
    this.board = new Board();
  }

  @Test
  void shouldMoveInL() {
    var knight = this.pieceFactory.createKnights(Color.WHITE).get(0);
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

    assertEquals(expectedValidMoves, knight.getSuggestedPlays());
  }

  @Test
  void shouldBeBlockedByItsTeamMates() {
    var knight = this.pieceFactory.createKnights(Color.WHITE).get(0);
    this.board.placePiece("b1", knight);
    this.board.placePiece("c3", this.pieceFactory.createPawns(Color.WHITE).get(0));

    var expectedValidMoves = Set.of(
        new Move(Color.WHITE, new Position("b1"), new Position("a3")),
        new Move(Color.WHITE, new Position("b1"), new Position("d2"))
    );

    assertEquals(expectedValidMoves, knight.getSuggestedPlays());
  }
}
