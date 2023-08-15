package chess.pieces;

import chess.board.path.BoardPath;
import chess.board.path.BoardPathDirection;
import chess.plays.Displacement;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class King extends Piece {
    private static final List<BoardPathDirection> pathDirections = List.of(BoardPathDirection.values());

    public King(Color color) {
        super(color, Type.KING);
    }

    public Set<Displacement> getValidMoves() {
        var movements = new HashSet<Displacement>();

        for (var kingPathDirection : King.pathDirections) {
            var path = new BoardPath(this.board.getMyPosition(), kingPathDirection);
            var targetPosition = path.getNextPosition();
            if (targetPosition.isPresent()) {
                var pieceAtTargetPosition = this.board.getPieceAt(targetPosition.get());
                if (pieceAtTargetPosition.isEmpty()) {
                    movements.add(new Displacement(this.board.getMyPosition(), targetPosition.get()));
                }
            }
        }

        return Collections.unmodifiableSet(movements);
    }
}
