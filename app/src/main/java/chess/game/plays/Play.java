package chess.game.plays;

import chess.game.board.BoardHistory;
import chess.game.board.BoardState;
import chess.game.pieces.Color;
import chess.game.plays.validation.PlayValidationError;

public interface Play {

  Runnable validateAndGetAction(BoardState boardState, BoardHistory boardHistory)
      throws PlayValidationError;

  default void actOn(BoardState boardState, BoardHistory boardHistory) throws PlayValidationError {
    var action = this.validateAndGetAction(boardState, boardHistory);
    action.run();
  }

  Color getPlayerColor();

  PlayDto toDto();
}
