package chess.game.plays.validation;

import chess.game.board.pieces.Color;

public class CantCastleOnKingThatAlreadyMoved extends PlayValidationError {
  public CantCastleOnKingThatAlreadyMoved(Color color) {
    super(color + " can't castle because it's king already moved.");
  }
}
