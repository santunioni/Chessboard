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

public class PawnMoveTest {

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
        new Move(PieceType.PAWN, PieceColor.WHITE, new Position("h7"), new Position("h8")));
  }

  @Test
  void shouldBeAbleToMoveExactlyOneSquareUp() {
    var pawn = this.pieceFactory.createPawns(PieceColor.WHITE).get(0);
    this.board.placePiece("a4", pawn);

    assertEquals(Set.of(
        new Move(PieceType.PAWN, PieceColor.WHITE, new Position("a4"), new Position("a5"))
    ), pawn.getSuggestedPlays());
  }

  @Test
  void shouldBeAbleToMoveExactlyOneSquareDown() {
    forwardToBlackTurn();
    var pawn = this.pieceFactory.createPawns(PieceColor.BLACK).get(0);
    this.board.placePiece("b4", pawn);

    assertEquals(Set.of(
        new Move(PieceType.PAWN, PieceColor.BLACK, new Position("b4"), new Position("b3"))
    ), pawn.getSuggestedPlays());
  }

  @Test
  void shouldBeAbleToMoveExactlyTwoSquaresUpIfHasNotMovedYet() {
    var pawn = this.pieceFactory.createPawns(PieceColor.WHITE).get(0);
    this.board.placePiece("c2", pawn);

    assertEquals(Set.of(
        new Move(PieceType.PAWN, PieceColor.WHITE, new Position("c2"), new Position("c3")),
        new Move(PieceType.PAWN, PieceColor.WHITE, new Position("c2"), new Position("c4"))
    ), pawn.getSuggestedPlays());
  }

  @Test
  void shouldBeAbleToMoveExactlyTwoSquaresDownIfHasNotMovedYet() {
    forwardToBlackTurn();
    var pawn = this.pieceFactory.createPawns(PieceColor.BLACK).get(0);
    this.board.placePiece("d7", pawn);

    assertEquals(Set.of(
        new Move(PieceType.PAWN, PieceColor.BLACK, new Position("d7"), new Position("d6")),
        new Move(PieceType.PAWN, PieceColor.BLACK, new Position("d7"), new Position("d5"))
    ), pawn.getSuggestedPlays());
  }

  @Test
  void shouldBeBlockedByOtherPieces() {
    var pawn = this.pieceFactory.createPawns(PieceColor.WHITE).get(0);
    this.board.placePiece("e2", pawn);
    this.board.placePiece("e3", this.pieceFactory.createPawns(PieceColor.BLACK).get(0));

    assertEquals(0, pawn.getSuggestedPlays().size());
  }
}
