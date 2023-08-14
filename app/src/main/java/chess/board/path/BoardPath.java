package chess.board.path;

import chess.board.position.Position;

import java.util.Iterator;


public record BoardPath(Position position, BoardPathOrientation orientation) implements Iterable<Position> {

    public Iterator<Position> iterator() {
        return new BoardPathIterator(this.position, this.orientation);
    }
}
