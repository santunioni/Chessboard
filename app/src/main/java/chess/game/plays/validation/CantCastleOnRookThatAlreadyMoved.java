package chess.game.plays.validation;

import chess.game.board.pieces.Color;
import chess.game.grid.Position;

public class CantCastleOnRookThatAlreadyMoved extends PlayValidationError {
  public CantCastleOnRookThatAlreadyMoved(Color color, Position rook) {
    super(color + " can't castle on rook " + rook + " because the rook already moved.");
  }
}
