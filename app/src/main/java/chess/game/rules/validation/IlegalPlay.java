package chess.game.rules.validation;

public class IlegalPlay extends Throwable {
    public IlegalPlay(String message) {
        super(message);
    }
}
