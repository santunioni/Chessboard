package chess.game.rules;

import chess.game.assertions.BoardStateIsValidAssertion;
import chess.game.board.Board;
import chess.game.plays.Play;
import chess.game.plays.validation.PlayValidationError;

public class PlayValidator {
  private final Play play;

  public PlayValidator(Play play) {
    this.play = play;
  }

  public boolean test(Board board) {
    try {
      CantPlayWhenNotYourTurn.validateHistoryBeforePlay(board, play);
      var boardCopy = board.createStateValidationCopy();
      boardCopy.makePlay(play);
      return new BoardStateIsValidAssertion().test(boardCopy);
    } catch (PlayValidationError e) {
      return false;
    }
  }
}
