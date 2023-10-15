package chess.game.rules;

import chess.game.board.ReadonlyBoard;
import chess.game.plays.Play;
import chess.game.rules.validation.NotYourTurn;

public class CantPlayWhenNotYourTurn {
  private CantPlayWhenNotYourTurn() {
  }

  public static void validateHistoryBeforePlay(ReadonlyBoard board, Play play) throws NotYourTurn {
    if (play.getPlayerColor() != board.nextTurnPlayerColor()) {
      throw new NotYourTurn(play);
    }
  }
}
