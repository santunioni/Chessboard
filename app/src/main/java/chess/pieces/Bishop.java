package chess.pieces;

import chess.board.path.BoardPath;
import chess.board.path.BoardPathDirection;
import chess.board.position.Position;
import chess.plays.Displacement;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Bishop extends Piece {
    private static final Set<BoardPathDirection> pathDirections = Set.of(
            BoardPathDirection.DIAGONAL_UP_LEFT,
            BoardPathDirection.DIAGONAL_UP_RIGHT,
            BoardPathDirection.DIAGONAL_DOWN_LEFT,
            BoardPathDirection.DIAGONAL_DOWN_RIGHT
    );

    public Bishop(Color color) {
        super(color, Type.BISHOP);
    }

    @Override
    public boolean threatens(Position enemyPosition) {
        var direction = this.board.getMyPosition().directionTo(enemyPosition);

        if (direction.isEmpty() || !Bishop.pathDirections.contains(direction.get())) {
            return false;
        }

        var path = new BoardPath(this.board.getMyPosition(), direction.get());
        for (var position : path) {
            if (position.equals(enemyPosition)) {
                return true;
            }
            if (this.board.getPieceAt(position).isPresent()) {
                return false;
            }
        }

        return false;
    }

    public Set<Displacement> getValidMoves() {
        var movements = new HashSet<Displacement>();

        for (var queenPathDirection : Bishop.pathDirections) {
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
