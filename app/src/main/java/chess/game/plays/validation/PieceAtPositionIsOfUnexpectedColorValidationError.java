package chess.game.plays.validation;

import chess.game.grid.Position;
import chess.game.pieces.Color;

public class PieceAtPositionIsOfUnexpectedColorValidationError extends PlayValidationError {
  public PieceAtPositionIsOfUnexpectedColorValidationError(Position at, Color expectedColor) {
    super("Piece color at " + at + " is not " + expectedColor + ".");
  }
}
