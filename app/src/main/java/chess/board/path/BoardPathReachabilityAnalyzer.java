package chess.board.path;

import chess.board.BoardPieceAtPositionProvider;
import chess.board.position.Position;

import java.util.Set;

public class BoardPathReachabilityAnalyzer {

    private final BoardPieceAtPositionProvider board;

    public BoardPathReachabilityAnalyzer(BoardPieceAtPositionProvider board) {
        this.board = board;
    }

    public boolean isReachableWalkingInOneOfDirections(
            Position from,
            Set<BoardPathDirection> allowedDirections,
            Position target
    ) {
        return this.isReachableWalkingInOneOfDirections(from, allowedDirections, target, 8);
    }

    public boolean isReachableWalkingInOneOfDirections(Position from, Set<BoardPathDirection> allowedDirections, Position target, int maxSteps) {
        var stepsTaken = 0;
        var direction = from.directionTo(target);

        if (direction.isEmpty() || !allowedDirections.contains(direction.get())) {
            return false;
        }

        var path = new BoardPath(from, direction.get());
        for (var step : path) {
            if (step.equals(target)) {
                return true;
            }
            if (this.board.getPieceAt(step).isPresent()) {
                break;
            }
            stepsTaken++;
            if (stepsTaken >= maxSteps) {
                break;
            }
        }

        return false;
    }
}
