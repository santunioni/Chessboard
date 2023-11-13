package chess.domain.play.validation;

public class PlayValidationError extends Throwable {
  public PlayValidationError(String message) {
    super(message);
  }
}
