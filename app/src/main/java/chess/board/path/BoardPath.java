package chess.board.path;

import chess.board.position.Position;

import java.util.Iterator;


public record BoardPath(Position position, BoardPathOrientation orientation, Integer steps) implements Iterable<Position> {

    public BoardPath(Position position, BoardPathOrientation orientation) {
        this(position, orientation, 8);
    }

    public Iterator<Position> iterator() {
        return new BoardPathIterator(this.position, this.orientation, this.steps);
    }
}
