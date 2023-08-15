package chess.pieces;

import chess.board.Color;
import chess.board.path.BoardPath;
import chess.board.path.BoardPathDirection;
import chess.board.position.Position;
import chess.plays.Displacement;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Bishop implements Piece {
    static final List<BoardPathDirection> pathDirections = List.of(
            BoardPathDirection.DIAGONAL_UP_LEFT,
            BoardPathDirection.DIAGONAL_UP_RIGHT,
            BoardPathDirection.DIAGONAL_DOWN_LEFT,
            BoardPathDirection.DIAGONAL_DOWN_RIGHT
    );

    final Color color;
    Position position;

    Bishop(Color color, Position position) {
        this.position = position;
        this.color = color;
    }


    public Set<Displacement> getValidMoves() {
        var movements = new HashSet<Displacement>();

        for (var bishopPathDirection : Bishop.pathDirections) {
            var path = new BoardPath(this.position, bishopPathDirection);
            for (var position : path) {
                movements.add(new Displacement(this.position, position));
            }
        }

        return Collections.unmodifiableSet(movements);
    }
}
