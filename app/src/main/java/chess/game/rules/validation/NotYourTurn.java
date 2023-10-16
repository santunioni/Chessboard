package chess.game.rules.validation;

import chess.game.plays.Play;

public class NotYourTurn extends IllegalPlay {
  public NotYourTurn(Play play) {
    super("It is not " + play.getPlayerColor() + " turn.");
  }
}
