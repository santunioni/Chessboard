package chess.game.plays.validation;

import chess.game.grid.Position;
import chess.game.pieces.Color;

public class CantCastleToInvalidPosition extends PlayValidationError {
    public CantCastleToInvalidPosition(Color color, Position to) {
        super("Cant castle " + color + " on invalid position " + to + ".");
    }
}
