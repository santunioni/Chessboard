package chess.game.plays.validation;

import chess.game.board.pieces.Color;
import chess.game.board.pieces.Pawn;

public class CantEnPassantOnInvalidRank extends PlayValidationError {
  public CantEnPassantOnInvalidRank(Color color) {
    super(color + " can only En Passant on rank " + Pawn.getEnPassantRank(color) + ".");
  }
}
