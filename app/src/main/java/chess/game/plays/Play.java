package chess.game.plays;

import chess.game.board.Board;
import chess.game.board.pieces.Color;
import chess.game.plays.validation.PlayValidationError;

public interface Play {

  Runnable validateAndGetAction(Board board)
      throws PlayValidationError;

  default boolean passesValidationsOn(Board board) {
    try {
      this.validateAndGetAction(board);
      return true;
    } catch (PlayValidationError ignored) {
      return false;
    }
  }

  default void actOn(Board board) throws PlayValidationError {
    var action = this.validateAndGetAction(board);
    action.run();
  }

  Color getPlayerColor();

  PlayDto toDto();
}
