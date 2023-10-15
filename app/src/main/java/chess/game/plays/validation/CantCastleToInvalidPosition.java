package chess.game.plays.validation;

import chess.game.board.pieces.Color;
import chess.game.grid.Position;

public class CantCastleToInvalidPosition extends PlayValidationError {
  public CantCastleToInvalidPosition(Color color, Position to) {
    super("Cant castle " + color + " on invalid position " + to + ".");
  }
}
