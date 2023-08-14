package chess.pieces;

import chess.board.Color;
import chess.board.path.BoardPathOrientation;
import chess.board.path.BoardPathWalker;
import chess.board.position.Movement;
import chess.board.position.Position;

import java.util.ArrayList;
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

        for (var firstStepSize : new ArrayList<>(List.of(2, 1))) {
            var lastStepSize = 3 - firstStepSize;
            for (var firstStepDirection : new ArrayList<>(List.of(BoardPathOrientation.HORIZONTAL_LEFT, BoardPathOrientation.HORIZONTAL_RIGHT))) {
                walker.goTo(firstStepSize, firstStepDirection).ifPresent(l -> {
                    l.goTo(lastStepSize, BoardPathOrientation.VERTICAL_UP).ifPresent(lu -> movements.add(new Movement(this.position, lu.getPosition())));
                    l.goTo(lastStepSize, BoardPathOrientation.VERTICAL_DOWN).ifPresent(ld -> movements.add(new Movement(this.position, ld.getPosition())));
                });
            }
        }

        return movements;
    }
}
