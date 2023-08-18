package chess.pieces;

import chess.board.path.BoardPath;
import chess.board.path.BoardPathDirection;
import chess.board.path.BoardPathReachabilityAnalyzer;
import chess.board.position.Position;
import chess.plays.Displacement;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Bishop extends Piece {


    public Bishop(Color color) {
        super(color, Type.BISHOP);
    }

    public boolean threatens(Position threatenedPosition) {
        return new BoardPathReachabilityAnalyzer(this.board).isReachableWalkingInOneOfDirections(
                this.board.getMyPosition(),
                BoardPathDirection.diagonals(),
                threatenedPosition
        );
    }

    public Set<Displacement> getValidMoves() {
        var movements = new HashSet<Displacement>();

        for (var queenPathDirection : BoardPathDirection.diagonals()) {
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
