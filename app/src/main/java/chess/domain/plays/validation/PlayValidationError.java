package chess.domain.plays.validation;

public class PlayValidationError extends Throwable {
  public PlayValidationError(String message) {
    super(message);
  }
}
