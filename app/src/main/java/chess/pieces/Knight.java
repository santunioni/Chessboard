package chess.pieces;

import chess.board.Color;
import chess.board.path.BoardPathOrientation;
import chess.board.path.BoardPathWalker;
import chess.board.position.Movement;
import chess.board.position.Position;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Knight implements Piece {

    final Color color;
    Position position;

    public Knight(Color color, Position position) {
        this.position = position;
        this.color = color;
    }

    @Override
    public Set<Movement> getValidMoves() {
        var movements = new HashSet<Movement>();

        var walker = new BoardPathWalker(this.position);

        for (var firstStepSize : List.of(2, 1)) {
            var lastStepSize = 3 - firstStepSize;
            for (var firstStepDirection : List.of(BoardPathOrientation.HORIZONTAL_LEFT, BoardPathOrientation.HORIZONTAL_RIGHT)) {
                walker.walk(firstStepSize, firstStepDirection).ifPresent(h -> {
                    h.walk(lastStepSize, BoardPathOrientation.VERTICAL_UP).ifPresent(u -> movements.add(new Movement(this.position, u.getPosition())));
                    h.walk(lastStepSize, BoardPathOrientation.VERTICAL_DOWN).ifPresent(d -> movements.add(new Movement(this.position, d.getPosition())));
                });
            }
        }

        return movements;
    }
}
