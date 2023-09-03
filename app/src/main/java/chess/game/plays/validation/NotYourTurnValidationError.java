package chess.game.plays.validation;

import chess.game.plays.Play;

public class NotYourTurnValidationError extends PlayValidationError {
    public NotYourTurnValidationError(Play play) {
        super("It is not " + play.getPlayerColor() + " turn.");
    }
}
