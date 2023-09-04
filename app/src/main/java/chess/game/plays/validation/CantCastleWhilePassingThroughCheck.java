package chess.game.plays.validation;

import chess.game.grid.Position;
import chess.game.pieces.Color;

public class CantCastleWhilePassingThroughCheck extends PlayValidationError {
  public CantCastleWhilePassingThroughCheck(Color color, Position rook) {
    super(color + " can't castle to " + rook + " while passing through Check.");
  }
}
