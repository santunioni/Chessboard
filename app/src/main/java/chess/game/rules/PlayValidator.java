package chess.game.rules;

import chess.game.board.Board;
import chess.game.plays.Play;
import chess.game.plays.validation.PlayValidationError;
import chess.game.rules.validation.IlegalPlay;

public class PlayValidator {
  private final Board board;

  public PlayValidator(Board board) {
    this.board = board;
  }

  public void validateNextPlay(Play play)
      throws PlayValidationError, IlegalPlay {
    play.validateAndGetAction(this.board);
    CantPlayWhenNotYourTurn.validateHistoryBeforePlay(this.board, play);

    var boardCopy = this.board.copy();
    play.actOn(boardCopy);

    CantLetKingInCheck.validateStateAfterPlay(boardCopy, play);
    PawnShouldBePromoted.validateStateAfterPlay(boardCopy, play);
  }
}
