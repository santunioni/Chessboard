package chess.game.plays.validation;

import chess.game.grid.Position;

public class NoPieceAtPositionValidationError extends PlayValidationError {
    public NoPieceAtPositionValidationError(Position at) {
        super("No piece at " + at);
    }
}
