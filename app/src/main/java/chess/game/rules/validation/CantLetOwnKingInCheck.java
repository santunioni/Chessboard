package chess.game.rules.validation;

import chess.game.grid.Position;
import chess.game.pieces.Color;
import chess.game.pieces.Piece;

public class CantLetOwnKingInCheck extends IlegalPlay {
    public CantLetOwnKingInCheck(Color kingColor, Position at, Piece threatener) {
        super("Can't let your own King (" + kingColor + ") at " + at + " in check by " + threatener + ".");
    }
}
