package chess.pieces;

import chess.board.Color;
import chess.board.path.BoardPath;
import chess.board.path.BoardPathOrientation;
import chess.board.position.Movement;
import chess.board.position.Position;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Rook implements Piece {
    final Color color;
    Position position;

    public Rook(Color color, Position position) {
        this.color = color;
        this.position = position;
    }

    @Override
    public Set<Movement> getValidMoves() {
        var validMoves = new HashSet<Movement>();

        var paths = new ArrayList<>(List.of(
                new BoardPath(this.position, BoardPathOrientation.VERTICAL_UP),
                new BoardPath(this.position, BoardPathOrientation.VERTICAL_DOWN),
                new BoardPath(this.position, BoardPathOrientation.HORIZONTAL_LEFT),
                new BoardPath(this.position, BoardPathOrientation.HORIZONTAL_RIGHT)
        ));

        for (var path : paths) {
            for (var position : path) {
                validMoves.add(new Movement(this.position, position));
            }
        }

        return validMoves;
    }
}
