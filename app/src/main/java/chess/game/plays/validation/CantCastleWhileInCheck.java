package chess.game.plays.validation;

import chess.game.pieces.Color;

public class CantCastleWhileInCheck extends PlayValidationError {
    CantCastleWhileInCheck(Color color) {
        super(color + " can't castle because it's king is in check.");
    }
}
