package chess.game.plays.validation;

import chess.game.board.pieces.Color;
import chess.game.grid.Position;

public class CantCastleOverOccupiedSquares extends PlayValidationError {

  public CantCastleOverOccupiedSquares(Color color, Position rook) {
    super(color + " can't castle to " + rook + " over occupied squares.");
  }
}
