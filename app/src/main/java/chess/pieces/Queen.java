package chess.pieces;

import chess.board.path.BoardPath;
import chess.board.path.BoardPathDirection;
import chess.board.position.Position;
import chess.plays.Displacement;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Queen extends Piece {
    private static final Set<BoardPathDirection> pathDirections = Set.of(BoardPathDirection.values());

    public Queen(Color color) {
        super(color, Type.QUEEN);
    }


    public boolean threatens(Position enemyPosition) {
        return this.canReachPositionByWalkingInOneOfDirections(enemyPosition, Queen.pathDirections);
    }

    public Set<Displacement> getValidMoves() {
        var movements = new HashSet<Displacement>();

        for (var queenPathDirection : Queen.pathDirections) {
            var path = new BoardPath(this.board.getMyPosition(), queenPathDirection);
            for (var targetPosition : path) {
                var pieceAtTargetPosition = this.board.getPieceAt(targetPosition);
                if (pieceAtTargetPosition.isEmpty()) {
                    movements.add(new Displacement(this.board.getMyPosition(), targetPosition));
                } else {
                    break;
                }
            }
        }

        return Collections.unmodifiableSet(movements);
    }
}
