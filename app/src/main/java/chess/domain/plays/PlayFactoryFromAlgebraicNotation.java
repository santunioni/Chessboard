package chess.domain.plays;

import chess.domain.grid.Position;
import chess.domain.pieces.Color;
import chess.domain.pieces.PieceType;
import chess.domain.plays.validation.PlayValidationError;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PlayFactoryFromAlgebraicNotation<R> {

  private static final Pattern algebraicNotationPattern =
      Pattern.compile("^([KQRBN]?)([a-h][1-8])(x?)([a-h][1-8])(?:=([QRBN]))?( e\\.p\\.)?$");
  private final GenericPlayFactory<R> innerFactory;

  public PlayFactoryFromAlgebraicNotation(GenericPlayFactory<R> innerFactory) {
    this.innerFactory = innerFactory;
  }

  public R createPlayFromLongAlgebraicNotation(Color color, String algebraic)
      throws PlayValidationError {
    if (algebraic.equals("0-0")) {
      return this.innerFactory.createCastle(color, CastleSide.KING_SIDE);
    }
    if (algebraic.equals("0-0-0")) {
      return this.innerFactory.createCastle(color, CastleSide.QUEEN_SIDE);
    }

    final Matcher matcher = algebraicNotationPattern.matcher(algebraic);
    if (!matcher.matches()) {
      throw new PlayValidationError("Invalid play: " + algebraic);
    }

    final PieceType type = PieceType.fromAlgebraicNotationChar(matcher.group(1));
    final Position from = new Position(matcher.group(2));
    final boolean isCapture = matcher.group(3).equals("x");
    final Position to = new Position(matcher.group(4));
    final Optional<PieceType> promotedToType =
        Optional.ofNullable(matcher.group(5))
            .map(PieceType::fromAlgebraicNotationChar);
    final boolean isEnPassant = Optional.ofNullable(matcher.group(6)).isPresent();

    if (isEnPassant && type.equals(PieceType.PAWN)) {
      return this.innerFactory.createEnPassant(color, from, to);
    }

    if (promotedToType.isPresent()) {
      if (isCapture) {
        return this.innerFactory.createPromotionAfterCapture(color, from, to,
            promotedToType.get());
      } else {
        return this.innerFactory.createPromotionAfterMove(color, from, to,
            promotedToType.get());
      }
    }

    if (isCapture) {
      return this.innerFactory.createCapture(type, color, from, to);
    } else {
      return this.innerFactory.createMove(type, color, from, to);
    }
  }
}
