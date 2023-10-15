package chess.game.plays;

import static chess.game.plays.PlayFactory.createPlayFromLongAlgebraicNotation;
import static org.junit.jupiter.api.Assertions.assertEquals;

import chess.game.board.pieces.Color;
import chess.game.grid.Position;
import chess.game.plays.validation.PlayValidationError;
import org.junit.jupiter.api.Test;

public class PlayFactoryTest {

  @Test
  void shouldReturnBishopCapturesPieceOnE5() throws PlayValidationError {
    assertEquals(new Capture(Color.WHITE, new Position("d4"), new Position("e5")),
        createPlayFromLongAlgebraicNotation("Bd4xe5"));
  }

  @Test
  void shouldReturnRookCapturesD7() throws PlayValidationError {
    assertEquals(new Capture(Color.WHITE, new Position("d3"), new Position("d7")),
        createPlayFromLongAlgebraicNotation("Rd3xd7"));
  }

  @Test
  void shouldReturnKnightMovesToC3() throws PlayValidationError {
    assertEquals(new Move(Color.WHITE, new Position("b1"), new Position("c3")),
        createPlayFromLongAlgebraicNotation("Nb1-c3"));
  }

  @Test
  void shouldReturnWhiteKingCastleOnRight() throws PlayValidationError {
    assertEquals(new Castle(Color.WHITE, new Position("h1")),
        createPlayFromLongAlgebraicNotation("Ke1-g1"));
  }

  @Test
  void shouldReturnWhiteKingCastleOnLeft() throws PlayValidationError {
    assertEquals(new Castle(Color.WHITE, new Position("a1")),
        createPlayFromLongAlgebraicNotation("Ke1-c1"));
  }

  @Test
  void shouldReturnBlackKingCastleOnRight() throws PlayValidationError {
    assertEquals(new Castle(Color.BLACK, new Position("h8")),
        createPlayFromLongAlgebraicNotation("ke8-g8"));
  }

  @Test
  void shouldReturnBlackKingCastleOnLeft() throws PlayValidationError {
    assertEquals(new Castle(Color.BLACK, new Position("a8")),
        createPlayFromLongAlgebraicNotation("ke8-c8"));
  }
}
