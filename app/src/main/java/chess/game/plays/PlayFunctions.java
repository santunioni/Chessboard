package chess.game.plays;

import chess.game.board.BoardState;
import chess.game.grid.Position;
import chess.game.pieces.Color;
import chess.game.pieces.Piece;

public class PlayFunctions {
    public static Piece getPieceFromBoard(Color color, Position from, Play play, BoardState boardState) throws IlegalPlay {
        var pieceOptional = boardState.getPieceAt(from);
        if (pieceOptional.isEmpty()) {
            throw new IlegalPlay(play, "No piece at " + from);
        }

        var piece = pieceOptional.get();
        if (piece.getColor() != color) {
            throw new IlegalPlay(play, "Piece at " + from + " is not " + color + ".");
        }

        return piece;
    }
}
