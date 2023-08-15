package chess.pieces;

import chess.board.Color;
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
        super(color);
    }


    public Set<Displacement> getValidMoves() {
        var movements = new HashSet<Displacement>();

        for (var queenPathDirection : Queen.pathDirections) {
            var path = new BoardPath(this.getPosition(), queenPathDirection);
            for (var position : path) {
                movements.add(new Displacement(this.getPosition(), position));
            }
        }

        return Collections.unmodifiableSet(movements);
    }
}
