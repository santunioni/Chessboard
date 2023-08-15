package chess.pieces;

import chess.board.Color;
import chess.board.path.BoardPath;
import chess.board.path.BoardPathDirection;
import chess.board.position.File;
import chess.board.position.Position;
import chess.board.position.Rank;
import chess.plays.Displacement;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class King implements Piece {
    static final List<BoardPathDirection> pathDirections = List.of(BoardPathDirection.values());

    final Color color;
    Position position;

    King(Color color) {
        this.color = color;
        this.position = King.getInitialPosition(color);
    }

    King(Color color, Position position) {
        this.color = color;
        this.position = position;
    }

    private static Position getInitialPosition(Color color) {
        if (color == Color.WHITE) {
            return new Position(File.E, Rank.ONE);
        } else {
            return new Position(File.E, Rank.EIGHT);
        }
    }

    public Set<Displacement> getValidMoves() {
        var movements = new HashSet<Displacement>();

        for (var kingPathDirection : King.pathDirections) {
            var path = new BoardPath(this.position, kingPathDirection);
            path.getNextPosition().ifPresent(position -> movements.add(new Displacement(this.position, position)));
        }

        return Collections.unmodifiableSet(movements);
    }
}
