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

public class QueenMoveTest {

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
  public void shouldBeAbleToMoveHorizontalyVerticallyAndDiagonaly() {
    forwardToBlackTurn();
    var queen = this.pieceFactory.createQueen(Color.BLACK);
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

    assertEquals(expectedValidMoves, queen.getSuggestedPlays());
  }

  @Test
  void shouldBeBlockedByItsTeamMates() {
    var queen = this.pieceFactory.createQueen(Color.WHITE);
    this.board.placePiece("a1", queen);
    this.board.placePiece("e1", this.pieceFactory.createPawns(Color.WHITE).get(0));
    this.board.placePiece("a2", this.pieceFactory.createPawns(Color.WHITE).get(1));
    this.board.placePiece("b2", this.pieceFactory.createPawns(Color.WHITE).get(2));

    var expectedValidMoves = Set.of(
        new Move(Color.WHITE, new Position("a1"), new Position("b1")),
        new Move(Color.WHITE, new Position("a1"), new Position("c1")),
        new Move(Color.WHITE, new Position("a1"), new Position("d1"))
    );

    assertEquals(expectedValidMoves, queen.getSuggestedPlays());
  }
}
