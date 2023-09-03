package chess.game.rules;

import chess.game.board.BoardState;
import chess.game.grid.Position;
import chess.game.pieces.Color;
import chess.game.pieces.PieceProperties;
import chess.game.pieces.Type;
import chess.game.plays.IlegalPlay;
import chess.game.plays.Play;


public class CantPutOwnKingInCheckValidation {

    public void validateStateAfterPlay(
            BoardState state,
            Play play
    ) throws IlegalPlay {
        var possiblePositionsForKing = state.findPositionsWithPiece(new PieceProperties() {
            public Color getColor() {
                return play.getPlayerColor();
            }

            public Type getType() {
                return Type.KING;
            }
        });
        if (possiblePositionsForKing.size() != 1) {
            return;
        }
        Position ownKingPosition = possiblePositionsForKing.get(0);

        var enemyPieces = state.getPlayerPieces(play.getPlayerColor().opposite());
        for (var enemyPiece : enemyPieces) {
            if (enemyPiece.couldCaptureEnemyAt(ownKingPosition)) {
                throw new IlegalPlay(play, "Cant put your own King in check.");
            }
        }
    }
}
