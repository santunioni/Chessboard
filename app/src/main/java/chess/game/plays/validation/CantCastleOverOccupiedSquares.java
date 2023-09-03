package chess.game.plays.validation;

import chess.game.grid.Position;
import chess.game.pieces.Color;

public class CantCastleOverOccupiedSquares extends PlayValidationError {

    CantCastleOverOccupiedSquares(Color color, Position rook) {
        super(color + " can't castle to " + rook + " over occupied squares.");
    }
}
