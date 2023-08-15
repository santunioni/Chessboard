package chess.pieces;

import chess.board.Color;
import chess.board.path.BoardPath;
import chess.board.path.BoardPathDirection;
import chess.plays.Displacement;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Rook extends Piece {
    private static final List<BoardPathDirection> pathDirections = List.of(
            BoardPathDirection.VERTICAL_UP,
            BoardPathDirection.VERTICAL_DOWN,
            BoardPathDirection.HORIZONTAL_LEFT,
            BoardPathDirection.HORIZONTAL_RIGHT
    );

    public Rook(Color color) {
        super(color);
    }

    @Override
    public Set<Displacement> getValidMoves() {
        var movements = new HashSet<Displacement>();

        for (var rookPathDirection : Rook.pathDirections) {
            var path = new BoardPath(this.getPosition(), rookPathDirection);
            for (var position : path) {
                movements.add(new Displacement(this.getPosition(), position));
            }
        }

        return Collections.unmodifiableSet(movements);
    }
}
