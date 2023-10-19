package chess.domain.pieces;

import static org.junit.jupiter.api.Assertions.assertEquals;

import chess.domain.board.Board;
import chess.domain.grid.Position;
import chess.domain.plays.Move;
import chess.domain.plays.Play;
import com.google.common.collect.HashBiMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BishopMoveTest {

  private final PieceFactory pieceFactory = new PieceFactory();
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
  void shouldBeAbleToMoveDiagonally() {
    forwardToBlackTurn();
    var bishop = this.pieceFactory.createBishops(Color.BLACK).get(0);
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

    assertEquals(expectedValidMoves, bishop.getSuggestedPlays());
  }

  @Test
  void shouldBeBlockedByItsTeamMates() {
    var bishop = this.pieceFactory.createBishops(Color.WHITE).get(0);
    this.board.placePiece("b1", bishop);
    this.board.placePiece("d3", this.pieceFactory.createPawns(Color.WHITE).get(0));

    var expectedValidMoves = Set.of(
        new Move(Color.WHITE, new Position("b1"), new Position("a2")),
        new Move(Color.WHITE, new Position("b1"), new Position("c2"))
    );

    assertEquals(expectedValidMoves, bishop.getSuggestedPlays());
  }
}
