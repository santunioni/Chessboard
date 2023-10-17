package chess.game.assertions;

import chess.game.board.Board;
import chess.game.board.ReadonlyBoard;
import chess.game.plays.Play;
import chess.game.plays.validation.PlayValidationError;

public class IsPlayLegalAssertion implements BoardAssertion {
  private final Play play;

  public IsPlayLegalAssertion(Play play) {
    this.play = play;
  }

  public boolean test(ReadonlyBoard board) {
    try {
      return play.getPlayerColor() == board.nextTurnPlayerColor()
          && play.passesValidationsOn((Board) board)
          && new BoardStateIsValidAssertion().test(board.simulate(play));
    } catch (PlayValidationError e) {
      return false;
    }
  }
}
