package chess.game.plays.validation;

import chess.game.grid.Position;
import chess.game.pieces.Color;

public class CantCastleOnRookNotInitialPosition extends PlayValidationError {
    public CantCastleOnRookNotInitialPosition(Color color, Position to) {
        super("Cant castle " + color + " on invalid position " + to + ".");
    }
}
