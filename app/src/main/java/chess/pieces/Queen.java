package chess.pieces;

import chess.board.Color;
import chess.board.path.BoardPath;
import chess.board.path.BoardPathDirection;
import chess.board.position.File;
import chess.plays.Displacement;
import chess.board.position.Position;
import chess.board.position.Rank;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Queen implements Piece {
    static final List<BoardPathDirection> pathDirections = List.of(BoardPathDirection.values());

    final Color color;
    Position position;

    Queen(Color color, Position position) {
        this.position = position;
        this.color = color;
    }


    Queen(Color color) {
        this.color = color;
        this.position = Queen.getInitialPosition(color);
    }

    private static Position getInitialPosition(Color color) {
        if (color == Color.WHITE) {
            return new Position(File.D, Rank.ONE);
        } else {
            return new Position(File.D, Rank.EIGHT);
        }
    }

    public Set<Displacement> getValidMoves() {
        var movements = new HashSet<Displacement>();

        for (var queenPathDirection : Queen.pathDirections) {
            var path = new BoardPath(this.position, queenPathDirection);
            for (var position : path) {
                movements.add(new Displacement(this.position, position));
            }
        }

        return Collections.unmodifiableSet(movements);
    }
}
