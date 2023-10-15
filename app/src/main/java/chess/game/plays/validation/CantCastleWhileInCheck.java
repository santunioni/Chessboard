package chess.game.plays.validation;

import chess.game.board.pieces.Color;

public class CantCastleWhileInCheck extends PlayValidationError {
  public CantCastleWhileInCheck(Color color) {
    super(color + " can't castle because it's king is in check.");
  }
}
