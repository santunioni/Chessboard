package chess.game.plays.validation;

public class PlayValidationError extends Throwable {
  public PlayValidationError(String message) {
    super(message);
  }
}
