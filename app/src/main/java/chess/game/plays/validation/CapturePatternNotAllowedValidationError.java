package chess.game.plays.validation;

import chess.game.grid.Position;
import chess.game.pieces.Piece;

public class CapturePatternNotAllowedValidationError extends PlayValidationError {
    public CapturePatternNotAllowedValidationError(Piece piece, Position at, Position target) {
        super("Piece " + piece.toString() + " at " + at.toString() + " cant capture target at " + target.toString() + ".");
    }
}
