package chess.game.rules;

import chess.game.board.Board;
import chess.game.board.PlayHistory;
import chess.game.plays.Play;
import chess.game.plays.validation.PlayValidationError;
import chess.game.rules.validation.IlegalPlay;

public class PlayValidatorAgainstAllChessRules {
  private PlayValidatorAgainstAllChessRules() {
  }

  public static void validateNextPlay(Board state, PlayHistory history, Play play)
      throws PlayValidationError, IlegalPlay {
    play.validateAndGetAction(state, history);
    CantPlayWhenNotYourTurn.validateHistoryBeforePlay(history, play);

    var stateCopy = state.copy();
    var historyCopy = history.copy();
    play.actOn(stateCopy, historyCopy);

    CantLetOwnKingInCheck.validateStateAfterPlay(stateCopy, play);
  }
}
