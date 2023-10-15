package chess.game.plays.validation;

import chess.game.board.pieces.Color;
import chess.game.grid.Position;

public class PieceAtPositionIsOfUnexpectedColorValidationError extends PlayValidationError {
  public PieceAtPositionIsOfUnexpectedColorValidationError(Position at, Color expectedColor) {
    super("Piece color at " + at + " is not " + expectedColor + ".");
  }
}
