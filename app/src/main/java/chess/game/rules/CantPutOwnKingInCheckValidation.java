package chess.game.rules;

import chess.game.board.BoardState;
import chess.game.grid.Position;
import chess.game.pieces.Color;
import chess.game.pieces.PieceProperties;
import chess.game.pieces.Type;
import chess.game.plays.IlegalPlay;
import chess.game.plays.Play;

import java.util.Optional;


public class CantPutOwnKingInCheckValidation {

    private final BoardState state;

    CantPutOwnKingInCheckValidation(BoardState state) {
        this.state = state;
    }

    private Optional<Position> findKing(Color color) {
        var possiblePositionsForKing = state.findPositionsWithPiece(new PieceProperties() {
            public Color getColor() {
                return color;
            }

            public Type getType() {
                return Type.KING;
            }
        });
        if (possiblePositionsForKing.size() != 1) {
            return Optional.empty();
        }
        Position ownKingPosition = possiblePositionsForKing.get(0);
        return Optional.of(ownKingPosition);
    }

    public void validateStateAfterPlay(
            Play play
    ) throws IlegalPlay {
        var possiblePositionsForKing = this.findKing(play.getPlayerColor());
        if (possiblePositionsForKing.isEmpty()) {
            return;
        }
        Position ownKingPosition = possiblePositionsForKing.get();

        var enemyPieces = state.getPlayerPieces(play.getPlayerColor().opposite());
        for (var enemyPiece : enemyPieces) {
            if (enemyPiece.couldCaptureEnemyAt(ownKingPosition)) {
                throw new IlegalPlay(play, "Cant put your own King in check.");
            }
        }
    }
}
