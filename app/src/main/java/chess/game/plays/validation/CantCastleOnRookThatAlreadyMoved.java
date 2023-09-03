package chess.game.plays.validation;

import chess.game.grid.Position;
import chess.game.pieces.Color;

public class CantCastleOnRookThatAlreadyMoved extends PlayValidationError {
    public CantCastleOnRookThatAlreadyMoved(Color color, Position rook) {
        super(color + " can't castle on rook " + rook + " because the rook already moved.");
    }
}
