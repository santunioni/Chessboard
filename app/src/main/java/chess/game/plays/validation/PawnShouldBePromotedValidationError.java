package chess.game.plays.validation;


import chess.game.board.pieces.Color;
import chess.game.board.pieces.Pawn;

public class PawnShouldBePromotedValidationError extends PlayValidationError {
  public PawnShouldBePromotedValidationError(Color color) {
    super("All "
        + color
        + " pawns that reach "
        + Pawn.getPromotionRankFor(color)
        + " should be promoted.");
  }
}
