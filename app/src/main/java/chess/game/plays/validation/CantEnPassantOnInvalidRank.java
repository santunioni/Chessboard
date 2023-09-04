package chess.game.plays.validation;

import chess.game.pieces.Color;
import chess.game.pieces.Pawn;

public class CantEnPassantOnInvalidRank extends PlayValidationError {
  public CantEnPassantOnInvalidRank(Color color) {
    super(color + " can only En Passant on rank " + Pawn.getEnPassantRank(color) + ".");
  }
}
