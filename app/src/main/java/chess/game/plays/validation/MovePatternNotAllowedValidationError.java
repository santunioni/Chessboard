package chess.game.plays.validation;

import chess.game.grid.Position;
import chess.game.pieces.Piece;

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
