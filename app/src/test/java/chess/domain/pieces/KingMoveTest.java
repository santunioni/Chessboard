package chess.domain.pieces;

import static org.junit.jupiter.api.Assertions.assertEquals;

import chess.domain.board.Board;
import chess.domain.board.PieceColor;
import chess.domain.board.PieceFactory;
import chess.domain.board.PieceType;
import chess.domain.grid.Position;
import chess.domain.play.Move;
import chess.domain.play.Play;
import com.google.common.collect.HashBiMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class KingMoveTest {

  private final PieceFactory pieceFactory = new PieceFactory();
  private Board board;
  private List<Play> stack;

  @BeforeEach
  void setUp() {
    this.stack = new ArrayList<>();
    this.board = new Board(UUID.randomUUID().toString(), HashBiMap.create(), stack);
  }

  private void forwardToBlackTurn() {
    this.stack.add(
        new Move(PieceType.KING, PieceColor.WHITE, new Position("h7"), new Position("h8")));
  }

  @Test
  void shouldBeAbleToMoveExactlyOneSquareInAnyDirection() {
    forwardToBlackTurn();
    var king = this.pieceFactory.createKing(PieceColor.BLACK);
    this.board.placePieceAt("d4", king);

    var expectedValidMoves = Set.of(
        new Move(PieceType.KING, PieceColor.BLACK, new Position("d4"), new Position("c3")),
        new Move(PieceType.KING, PieceColor.BLACK, new Position("d4"), new Position("d3")),
        new Move(PieceType.KING, PieceColor.BLACK, new Position("d4"), new Position("e3")),

        new Move(PieceType.KING, PieceColor.BLACK, new Position("d4"), new Position("c4")),
        new Move(PieceType.KING, PieceColor.BLACK, new Position("d4"), new Position("e4")),

        new Move(PieceType.KING, PieceColor.BLACK, new Position("d4"), new Position("c5")),
        new Move(PieceType.KING, PieceColor.BLACK, new Position("d4"), new Position("d5")),
        new Move(PieceType.KING, PieceColor.BLACK, new Position("d4"), new Position("e5"))
    );

    assertEquals(expectedValidMoves, king.getSuggestedPlays());
  }

  @Test
  void shouldBeBlockedByWalls() {
    var king = this.pieceFactory.createKing(PieceColor.WHITE);
    this.board.placePieceAt("e1", king);

    var expectedValidMoves = Set.of(
        new Move(PieceType.KING, PieceColor.WHITE, new Position("e1"), new Position("d1")),
        new Move(PieceType.KING, PieceColor.WHITE, new Position("e1"), new Position("f1")),

        new Move(PieceType.KING, PieceColor.WHITE, new Position("e1"), new Position("d2")),
        new Move(PieceType.KING, PieceColor.WHITE, new Position("e1"), new Position("e2")),
        new Move(PieceType.KING, PieceColor.WHITE, new Position("e1"), new Position("f2"))
    );

    assertEquals(expectedValidMoves, king.getSuggestedPlays());
  }

  @Test
  void shouldBeBlockedByCorner() {
    var king = this.pieceFactory.createKing(PieceColor.WHITE);
    this.board.placePieceAt("a1", king);

    var expectedValidMoves = Set.of(
        new Move(PieceType.KING, PieceColor.WHITE, new Position("a1"), new Position("b1")),
        new Move(PieceType.KING, PieceColor.WHITE, new Position("a1"), new Position("a2")),
        new Move(PieceType.KING, PieceColor.WHITE, new Position("a1"), new Position("b2"))
    );

    assertEquals(expectedValidMoves, king.getSuggestedPlays());
  }

  @Test
  void shouldBeBlockedByItsTeamMates() {
    var king = this.pieceFactory.createKing(PieceColor.WHITE);
    this.board.placePieceAt("a1", king);
    this.board.placePieceAt("a2", this.pieceFactory.createPawns(PieceColor.WHITE).get(0));
    this.board.placePieceAt("b2", this.pieceFactory.createPawns(PieceColor.WHITE).get(1));

    var expectedValidMoves = Set.of(
        new Move(PieceType.KING, PieceColor.WHITE, new Position("a1"), new Position("b1"))
    );

    assertEquals(expectedValidMoves, king.getSuggestedPlays());
  }
}
