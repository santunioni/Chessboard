package chess.game.plays.validation;

import chess.game.board.pieces.Color;
import chess.game.grid.Position;

public class CantCastleWhilePassingThroughCheck extends PlayValidationError {
  public CantCastleWhilePassingThroughCheck(Color color, Position rook) {
    super(color + " can't castle to " + rook + " while passing through Check.");
  }
}
