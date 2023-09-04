package chess.game.grid;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;


public class BoardPath implements Iterable<Position> {

  private final Position position;
  private final BoardPathDirection direction;
  private final int maxSteps;

  public BoardPath(Position position, BoardPathDirection direction, int maxSteps) {
    this.position = position;
    this.direction = direction;
    this.maxSteps = maxSteps;
  }

  public BoardPath(Position position, BoardPathDirection direction) {
    this(position, direction, 8);
  }

  public Iterator<Position> iterator() {
    return new BoardPathIterator(this.position, this.direction, this.maxSteps);
  }

  public List<Position> toPositionList() {
    var path = new LinkedList<Position>();
    this.iterator().forEachRemaining(path::add);
    return path;
  }
}
