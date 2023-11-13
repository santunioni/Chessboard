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

public class RookMoveTest {

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
        new Move(PieceType.ROOK, PieceColor.WHITE, new Position("h7"), new Position("h8")));
  }

  @Test
  void shouldBeAbleToMoveVerticallyAndHorizontally() {
    forwardToBlackTurn();
    var rook = this.pieceFactory.createRooks(PieceColor.BLACK).get(0);
    this.board.placePiece("d4", rook);

    var expectedValidMoves = Set.of(
        new Move(PieceType.ROOK, PieceColor.BLACK, new Position("d4"), new Position("a4")),
        new Move(PieceType.ROOK, PieceColor.BLACK, new Position("d4"), new Position("b4")),
        new Move(PieceType.ROOK, PieceColor.BLACK, new Position("d4"), new Position("c4")),
        new Move(PieceType.ROOK, PieceColor.BLACK, new Position("d4"), new Position("e4")),
        new Move(PieceType.ROOK, PieceColor.BLACK, new Position("d4"), new Position("f4")),
        new Move(PieceType.ROOK, PieceColor.BLACK, new Position("d4"), new Position("g4")),
        new Move(PieceType.ROOK, PieceColor.BLACK, new Position("d4"), new Position("h4")),

        new Move(PieceType.ROOK, PieceColor.BLACK, new Position("d4"), new Position("d1")),
        new Move(PieceType.ROOK, PieceColor.BLACK, new Position("d4"), new Position("d2")),
        new Move(PieceType.ROOK, PieceColor.BLACK, new Position("d4"), new Position("d3")),
        new Move(PieceType.ROOK, PieceColor.BLACK, new Position("d4"), new Position("d5")),
        new Move(PieceType.ROOK, PieceColor.BLACK, new Position("d4"), new Position("d6")),
        new Move(PieceType.ROOK, PieceColor.BLACK, new Position("d4"), new Position("d7")),
        new Move(PieceType.ROOK, PieceColor.BLACK, new Position("d4"), new Position("d8"))
    );

    assertEquals(expectedValidMoves, rook.getSuggestedPlays());
  }

  @Test
  void shouldBeBlockedByItsTeamMates() {
    var rook = this.pieceFactory.createRooks(PieceColor.WHITE).get(0);
    this.board.placePiece("b1", rook);
    this.board.placePiece("b2", this.pieceFactory.createPawns(PieceColor.WHITE).get(0));
    this.board.placePiece("d1", this.pieceFactory.createPawns(PieceColor.WHITE).get(1));

    var expectedValidMoves = Set.of(
        new Move(PieceType.ROOK, PieceColor.WHITE, new Position("b1"), new Position("a1")),
        new Move(PieceType.ROOK, PieceColor.WHITE, new Position("b1"), new Position("c1"))
    );

    assertEquals(expectedValidMoves, rook.getSuggestedPlays());
  }
}
