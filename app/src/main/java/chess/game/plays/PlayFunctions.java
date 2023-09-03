package chess.game.plays;

import chess.game.board.BoardState;
import chess.game.grid.Position;
import chess.game.pieces.Color;
import chess.game.pieces.Piece;
import chess.game.plays.validation.NoPieceAtPositionValidationError;
import chess.game.plays.validation.PieceAtPositionIsOfUnexpectedColorValidationError;
import chess.game.plays.validation.PlayValidationError;

public class PlayFunctions {
    public static Piece getPieceFromBoard(Color expectedColor, Position from, BoardState boardState) throws PlayValidationError {
        var pieceOptional = boardState.getPieceAt(from);
        if (pieceOptional.isEmpty()) {
            throw new NoPieceAtPositionValidationError(from);
        }

        var piece = pieceOptional.get();
        if (piece.getColor() != expectedColor) {
            throw new PieceAtPositionIsOfUnexpectedColorValidationError(from, expectedColor);
        }

        return piece;
    }

    public static boolean isPositionThreatenedBy(BoardState state, Position position, Color color) {
        for (var piece : state.getPlayerPieces(color)) {
            if (piece.couldCaptureEnemyAt(position)) {
                return true;
            }
        }
        return false;
    }
}
