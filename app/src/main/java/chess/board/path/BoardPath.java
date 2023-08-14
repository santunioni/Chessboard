package chess.board.path;

import chess.board.position.Position;

import java.util.Iterator;


public class BoardPath implements Iterable<Position> {
    final Position position;
    final BoardPathOrientation orientation;

    public BoardPath(Position position, BoardPathOrientation orientation) {
        this.position = position;
        this.orientation = orientation;
    }

    public Iterator<Position> iterator() {
        return new BoardPathIterator(this.position, this.orientation);
    }
}
