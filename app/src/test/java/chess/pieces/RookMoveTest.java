package chess.pieces;

import static org.junit.jupiter.api.Assertions.assertEquals;

import chess.game.board.Board;
import chess.game.board.pieces.Color;
import chess.game.board.pieces.Pawn;
import chess.game.board.pieces.Rook;
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

public class RookMoveTest {

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
  void shouldBeAbleToMoveVerticallyAndHorizontally() {
    forwardToBlackTurn();
    var rook = new Rook(Color.BLACK);
    this.board.placePiece("d4", rook);

    var expectedValidMoves = Set.of(
        new Move(Color.BLACK, new Position("d4"), new Position("a4")),
        new Move(Color.BLACK, new Position("d4"), new Position("b4")),
        new Move(Color.BLACK, new Position("d4"), new Position("c4")),
        new Move(Color.BLACK, new Position("d4"), new Position("e4")),
        new Move(Color.BLACK, new Position("d4"), new Position("f4")),
        new Move(Color.BLACK, new Position("d4"), new Position("g4")),
        new Move(Color.BLACK, new Position("d4"), new Position("h4")),

        new Move(Color.BLACK, new Position("d4"), new Position("d1")),
        new Move(Color.BLACK, new Position("d4"), new Position("d2")),
        new Move(Color.BLACK, new Position("d4"), new Position("d3")),
        new Move(Color.BLACK, new Position("d4"), new Position("d5")),
        new Move(Color.BLACK, new Position("d4"), new Position("d6")),
        new Move(Color.BLACK, new Position("d4"), new Position("d7")),
        new Move(Color.BLACK, new Position("d4"), new Position("d8"))
    );

    assertEquals(expectedValidMoves, rook.getPlays(board));
  }

  @Test
  void shouldBeBlockedByItsTeamMates() {
    var rook = new Rook(Color.WHITE);
    this.board.placePiece("b1", rook);
    this.board.placePiece("b2", new Pawn(Color.WHITE));
    this.board.placePiece("d1", new Pawn(Color.WHITE));

    var expectedValidMoves = Set.of(
        new Move(Color.WHITE, new Position("b1"), new Position("a1")),
        new Move(Color.WHITE, new Position("b1"), new Position("c1"))
    );

    assertEquals(expectedValidMoves, rook.getPlays(board));
  }
}
