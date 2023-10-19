package chess.domain.plays;

import static org.junit.jupiter.api.Assertions.assertEquals;

import chess.domain.grid.Position;
import chess.domain.pieces.Color;
import chess.domain.pieces.PieceType;
import chess.domain.plays.validation.PlayValidationError;
import org.junit.jupiter.api.Test;

public class PlayFactoryTest {

  private final PlayFactory playFactory = new PlayFactory();

  @Test
  void shouldReturnBishopCapturesPieceOnE5() throws PlayValidationError {
    assertEquals(new Capture(PieceType.BISHOP, Color.WHITE, new Position("d4"), new Position("e5")),
        playFactory.createPlayFromLongAlgebraicNotation(Color.WHITE, "Bd4xe5"));
  }

  @Test
  void shouldReturnRookCapturesD7() throws PlayValidationError {
    assertEquals(new Capture(PieceType.ROOK, Color.WHITE, new Position("d3"), new Position("d7")),
        playFactory.createPlayFromLongAlgebraicNotation(Color.WHITE, "Rd3xd7"));
  }

  @Test
  void shouldReturnKnightMovesToC3() throws PlayValidationError {
    assertEquals(new Move(PieceType.KNIGHT, Color.WHITE, new Position("b1"), new Position("c3")),
        playFactory.createPlayFromLongAlgebraicNotation(Color.WHITE, "Nb1c3"));
  }

  @Test
  void shouldReturnWhiteKingCastleOnRight() throws PlayValidationError {
    assertEquals(new Castle(Color.WHITE, CastleSide.KING_SIDE),
        playFactory.createPlayFromLongAlgebraicNotation(Color.WHITE, "0-0"));
  }

  @Test
  void shouldReturnWhiteKingCastleOnLeft() throws PlayValidationError {
    assertEquals(new Castle(Color.WHITE, CastleSide.QUEEN_SIDE),
        playFactory.createPlayFromLongAlgebraicNotation(Color.WHITE, "0-0-0"));
  }
}
