package chess.game.rules;

import chess.game.board.Board;
import chess.game.board.PlayHistory;
import chess.game.plays.Play;
import chess.game.plays.validation.PlayValidationError;
import chess.game.rules.validation.IlegalPlay;

public class PlayValidator {
  private final Board board;
  private final PlayHistory history;

  public PlayValidator(Board board, PlayHistory history) {
    this.board = board;
    this.history = history;
  }

  public void validateNextPlay(Play play)
      throws PlayValidationError, IlegalPlay {
    play.validateAndGetAction(this.board, this.history);
    CantPlayWhenNotYourTurn.validateHistoryBeforePlay(this.history, play);

    var boardCopy = this.board.copy();
    var historyCopy = this.history.copy();
    play.actOn(boardCopy, historyCopy);

    CantLetKingInCheck.validateStateAfterPlay(boardCopy, play);
    PawnShouldBePromoted.validateStateAfterPlay(boardCopy, play);
  }
}
