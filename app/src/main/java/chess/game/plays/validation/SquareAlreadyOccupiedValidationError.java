package chess.game.plays.validation;

import chess.game.board.pieces.Piece;
import chess.game.grid.Position;

public class SquareAlreadyOccupiedValidationError extends PlayValidationError {
  public SquareAlreadyOccupiedValidationError(Position position, Piece piece) {
    super("Cant move to "
        + position.toString()
        + " because it is ocuppied by "
        + piece.toString()
        + ".");
  }
}
