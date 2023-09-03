package chess.game.plays.validation;

import chess.game.grid.Position;
import chess.game.pieces.Color;

public class CantCastleOnOtherPiecesThanRook extends PlayValidationError {
    CantCastleOnOtherPiecesThanRook(Color color, Position rook) {
        super(color + " can't castle on " + rook + " because it is not on its expected position.");
    }
}
