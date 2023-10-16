package chess.game.rules.validation;

import chess.game.board.pieces.Color;

public class CantLetOwnKingInCheckValidationError extends IllegalPlay {
  public CantLetOwnKingInCheckValidationError(Color kingColor) {
    super("Can't let your own King (" + kingColor + ") threatened.");
  }
}
