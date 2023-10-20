package chess.domain.plays;

import chess.domain.grid.Position;
import chess.domain.pieces.Color;
import chess.domain.pieces.PieceType;
import chess.domain.plays.validation.PlayValidationError;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PlayFactory {

  private static final Pattern algebraicNotationPattern =
      Pattern.compile("^([KQRBN]?)([a-h][1-8])(x?)([a-h][1-8])(=[QRBN])?( e\\.p\\.)?$");

  public Play createPlayFromLongAlgebraicNotation(Color color, String algebraic)
      throws PlayValidationError {
    return this.createCastle(color, algebraic)
        .or(() -> this.createMoveBasedOnPattern(color, algebraic))
        .orElseThrow(() -> new PlayValidationError("Invalid play: " + algebraic));
  }

  private Optional<Play> createCastle(Color color, String algebraic) {
    Optional<Play> play = Optional.empty();
    if (algebraic.equals("0-0")) {
      play = Optional.of(new Castle(color, CastleSide.KING_SIDE));
    } else if (algebraic.equals("0-0-0")) {
      play = Optional.of(new Castle(color, CastleSide.QUEEN_SIDE));
    }
    return play;
  }

  private Optional<Play> createMoveBasedOnPattern(Color color, String algebraic) {
    Optional<Play> play = Optional.empty();

    final Matcher matcher = algebraicNotationPattern.matcher(algebraic);

    if (matcher.matches()) {
      final PieceType type = selectPieceTypeFromString(matcher.group(1));
      final Position from = new Position(matcher.group(2));
      final boolean isCapture = matcher.group(3).equals("x");
      final Position to = new Position(matcher.group(4));
      final Optional<PieceType> promotedToType =
          Optional.ofNullable(matcher.group(5)).map(PlayFactory::selectPieceTypeFromString);
      final boolean isEnPassant = Optional.ofNullable(matcher.group(6)).isPresent();

      if (isEnPassant && type.equals(PieceType.PAWN)) {
        play = Optional.of(new EnPassant(color, from, to));
      } else if (isCapture) {
        final Capture capture = new Capture(type, color, from, to);
        play = Optional.of(
            promotedToType.isPresent() ? new Promotion(capture, promotedToType.get()) : capture
        );
      } else {
        final Move move = new Move(type, color, from, to);
        play = Optional.of(
            promotedToType.isPresent() ? new Promotion(move, promotedToType.get()) : move
        );
      }
    }

    return play;
  }

  private static PieceType selectPieceTypeFromString(String charLetter) {
    return switch (charLetter) {
      case "K" -> PieceType.KING;
      case "Q" -> PieceType.QUEEN;
      case "R" -> PieceType.ROOK;
      case "B" -> PieceType.BISHOP;
      case "N" -> PieceType.KNIGHT;
      default -> PieceType.PAWN;
    };
  }
}
