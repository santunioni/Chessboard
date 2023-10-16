package chess.game.rules.validation;

import chess.game.plays.validation.PlayValidationError;

public class IllegalPlay extends PlayValidationError {
  public IllegalPlay(String message) {
    super(message);
  }
}
