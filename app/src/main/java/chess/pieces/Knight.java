package chess.pieces;

import chess.board.path.BoardPathDirection;
import chess.board.path.BoardPathWalker;
import chess.board.position.Position;
import chess.plays.Displacement;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@FunctionalInterface
interface KnightTargetPositionsIteratorCallback {
    void call(Position position);
}


public class Knight extends Piece {

    public Knight(Color color) {
        super(color, Type.KNIGHT);
    }


    /**
     * Iterate over all possible target positions for a knight, making the L pattern.
     *
     * @param callback A callback that will be called for each possible target position.
     */
    private void iterateOverPositionsThatKnightCouldReachByMovingInLPattern(
            KnightTargetPositionsIteratorCallback callback
    ) {
        for (var firstStepSize : List.of(2, 1)) {
            var lastStepSize = 3 - firstStepSize;
            for (var horizontalDirection : List.of(BoardPathDirection.HORIZONTAL_LEFT, BoardPathDirection.HORIZONTAL_RIGHT)) {
                for (var verticalDirection : List.of(BoardPathDirection.VERTICAL_DOWN, BoardPathDirection.VERTICAL_UP)) {
                    var walker = new BoardPathWalker(this.board.getMyPosition());
                    var targetPosition = walker.walk(firstStepSize, verticalDirection).flatMap(v -> v.walk(lastStepSize, horizontalDirection)).map(BoardPathWalker::getPosition);
                    targetPosition.ifPresent(callback::call);
                }
            }
        }
    }

    @Override
    public Set<Displacement> getValidMoves() {
        var movements = new HashSet<Displacement>();

        this.iterateOverPositionsThatKnightCouldReachByMovingInLPattern(targetPosition -> {
            var pieceAtTargetPosition = this.board.getPieceAt(targetPosition);
            if (pieceAtTargetPosition.isEmpty() || pieceAtTargetPosition.get().getColor() != this.getColor()) {
                movements.add(new Displacement(this.board.getMyPosition(), targetPosition));
            }
        });

        return movements;
    }
}
