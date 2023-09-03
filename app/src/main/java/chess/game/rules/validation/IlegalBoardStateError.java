package chess.game.rules.validation;

public class IlegalBoardStateError extends Throwable {
    public IlegalBoardStateError(String message) {
        super(message);
    }
}
