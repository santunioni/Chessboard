package chess.game.plays.validation;

import static chess.game.plays.Promotion.getPromotionRankForColor;

import chess.game.pieces.Color;

public class PawnShouldBePromotedValidationError extends PlayValidationError {
  public PawnShouldBePromotedValidationError(Color color) {
    super("All "
        + color
        + " pawns that reach "
        + getPromotionRankForColor(color)
        + " should be promoted.");
  }
}
