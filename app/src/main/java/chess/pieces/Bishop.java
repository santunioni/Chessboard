package chess.pieces;

import chess.board.Color;
import chess.board.path.BoardPath;
import chess.board.path.BoardPathOrientation;
import chess.board.position.Movement;
import chess.board.position.Position;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Bishop implements Piece {

    final Color color;
    Position position;

    Bishop(Color color, Position position) {
        this.position = position;
        this.color = color;
    }


    public Set<Movement> getValidMoves() {
        var movements = new HashSet<Movement>();

        var orientations =
                List.of(
                        BoardPathOrientation.DIAGONAL_UP_LEFT,
                        BoardPathOrientation.DIAGONAL_UP_RIGHT,
                        BoardPathOrientation.DIAGONAL_DOWN_LEFT,
                        BoardPathOrientation.DIAGONAL_DOWN_RIGHT
                );

        for (var orientation : orientations) {
            var path = new BoardPath(this.position, orientation);
            for (var position : path) {
                movements.add(new Movement(this.position, position));
            }
        }

        return Collections.unmodifiableSet(movements);
    }
}
