package chess.game.grid;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;


public record BoardPath(Position position, BoardPathDirection direction) implements Iterable<Position> {


    public Iterator<Position> iterator() {
        return new BoardPathIterator(this.position, this.direction);
    }

    public List<Position> toPositionList() {
        var path = new LinkedList<Position>();
        this.iterator().forEachRemaining(path::add);
        return path;
    }
}
