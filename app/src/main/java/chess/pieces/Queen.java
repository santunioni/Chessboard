package chess.pieces;

import chess.board.path.BoardPath;
import chess.board.path.BoardPathDirection;
import chess.plays.Displacement;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Queen extends Piece {
    private static final List<BoardPathDirection> pathDirections = List.of(BoardPathDirection.values());

    public Queen(Color color) {
        super(color, Type.QUEEN);
    }


    public Set<Displacement> getValidMoves() {
        var movements = new HashSet<Displacement>();

        for (var queenPathDirection : Queen.pathDirections) {
            var path = new BoardPath(this.board.getMyPosition(), queenPathDirection);
            for (var position : path) {
                var pieceAtPosition = this.board.getPieceAt(position);
                if (pieceAtPosition.isPresent()) {
                    break;
                }
                movements.add(new Displacement(this.board.getMyPosition(), position));
            }
        }

        return Collections.unmodifiableSet(movements);
    }
}
