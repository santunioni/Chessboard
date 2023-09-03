package chess.game.rules.validation;

import chess.game.grid.Position;
import chess.game.pieces.Color;

public class CantLetOwnKingInCheck extends IlegalPlay {
    public CantLetOwnKingInCheck(Color kingColor, Position at) {
        super("Can't let your own King (" + kingColor + ") threated at " + at + ".");
    }
}
