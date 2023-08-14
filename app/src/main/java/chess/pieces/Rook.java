package chess.pieces;

import chess.board.Color;
import chess.board.path.BoardPath;
import chess.board.path.BoardPathOrientation;
import chess.board.position.Movement;
import chess.board.position.Position;

import java.util.*;

public class Rook implements Piece {
    final Color color;
    Position position;

    public Rook(Color color, Position position) {
        this.color = color;
        this.position = position;
    }

    @Override
    public Set<Movement> getValidMoves() {
        var movements = new HashSet<Movement>();

        var paths = new ArrayList<>(
                List.of(
                        BoardPathOrientation.VERTICAL_UP,
                        BoardPathOrientation.VERTICAL_DOWN,
                        BoardPathOrientation.HORIZONTAL_LEFT,
                        BoardPathOrientation.HORIZONTAL_RIGHT
                )
        );

        paths.forEach(orientation -> {
            new BoardPath(this.position, orientation).forEach(position -> movements.add(new Movement(this.position, position)));
        });

        return Collections.unmodifiableSet(movements);
    }
}
