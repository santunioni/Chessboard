package chess.game.plays.validation;

import chess.game.board.pieces.Piece;
import chess.game.grid.Position;

public class MovePatternNotAllowedValidationError extends PlayValidationError {
  public MovePatternNotAllowedValidationError(Piece piece, Position from, Position to) {
    super("Cant move "
        + piece.toString()
        + " from " + from.toString()
        + " to "
        + to.toString()
        + ".");
  }
}
