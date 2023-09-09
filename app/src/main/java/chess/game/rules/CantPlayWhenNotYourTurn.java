package chess.game.rules;

import chess.game.board.PlayHistory;
import chess.game.plays.Play;
import chess.game.rules.validation.NotYourTurn;

public class CantPlayWhenNotYourTurn {
  private CantPlayWhenNotYourTurn() {
  }

  public static void validateHistoryBeforePlay(PlayHistory history, Play play) throws NotYourTurn {
    if (play.getPlayerColor() != history.nextTurnPlayerColor()) {
      throw new NotYourTurn(play);
    }
  }
}
