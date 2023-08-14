package chess.board.path;

import chess.board.position.Position;

import java.util.Iterator;
import java.util.Optional;


public record BoardPath(Position position, BoardPathOrientation orientation) implements Iterable<Position> {


    public Iterator<Position> iterator() {
        return new BoardPathIterator(this.position, this.orientation);
    }

    public Optional<Position> getNextPosition() {
        var iterator = this.iterator();
        return iterator.hasNext() ? Optional.of(iterator.next()) : Optional.empty();
    }
}
