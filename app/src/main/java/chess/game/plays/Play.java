package chess.game.plays;

import chess.game.board.Board;
import chess.game.board.PlayHistory;
import chess.game.pieces.Color;
import chess.game.plays.validation.PlayValidationError;

public interface Play {

  Runnable validateAndGetAction(Board board, PlayHistory playHistory)
      throws PlayValidationError;

  default void actOn(Board board, PlayHistory playHistory) throws PlayValidationError {
    var action = this.validateAndGetAction(board, playHistory);
    action.run();
  }

  Color getPlayerColor();

  PlayDto toDto();
}
