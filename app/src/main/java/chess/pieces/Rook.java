package chess.pieces;

import chess.board.Color;
import chess.board.path.BoardPath;
import chess.board.path.BoardPathDirection;
import chess.plays.Displacement;
import chess.board.position.Position;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Rook implements Piece {
    static final List<BoardPathDirection> pathDirections = List.of(
            BoardPathDirection.VERTICAL_UP,
            BoardPathDirection.VERTICAL_DOWN,
            BoardPathDirection.HORIZONTAL_LEFT,
            BoardPathDirection.HORIZONTAL_RIGHT
    );

    final Color color;
    Position position;

    public Rook(Color color, Position position) {
        this.color = color;
        this.position = position;
    }

    @Override
    public Set<Displacement> getValidMoves() {
        var movements = new HashSet<Displacement>();

        for (var rookPathDirection : Rook.pathDirections) {
            var path = new BoardPath(this.position, rookPathDirection);
            for (var position : path) {
                movements.add(new Displacement(this.position, position));
            }
        }

        return Collections.unmodifiableSet(movements);
    }
}
