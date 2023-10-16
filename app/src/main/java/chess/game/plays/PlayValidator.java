package chess.game.plays;

import chess.game.assertions.BoardStateIsValidAssertion;
import chess.game.board.Board;
import chess.game.plays.validation.PlayValidationError;
import chess.game.rules.CantPlayWhenNotYourTurn;

public class PlayValidator {
  private final Play play;

  public PlayValidator(Play play) {
    this.play = play;
  }

  public boolean test(Board board) {
    try {
      CantPlayWhenNotYourTurn.validateHistoryBeforePlay(board, play);
      return new BoardStateIsValidAssertion().test(board.simulate(play));
    } catch (PlayValidationError e) {
      return false;
    }
  }
}
