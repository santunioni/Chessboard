package chess.game.grid;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import javax.annotation.Nonnull;


public class Path implements Iterable<Position> {

  private final Position position;
  private final Direction direction;
  private final int maxSteps;

  public Path(Position position, Direction direction, int maxSteps) {
    this.position = position;
    this.direction = direction;
    this.maxSteps = maxSteps;
  }

  public Path(Position position, Direction direction) {
    this(position, direction, 8);
  }

  @Nonnull
  public Iterator<Position> iterator() {
    return new Iterator<>() {
      private Position position = Path.this.position;
      private int stepsLeft = Path.this.maxSteps;

      public boolean hasNext() {
        return this.position.nextOn(direction).isPresent() && this.stepsLeft > 0;
      }

      public Position next() {
        this.position = this.position.nextOn(direction).orElseThrow();
        this.stepsLeft--;
        return this.position;
      }
    };
  }

  public List<Position> toPositionList() {
    var path = new LinkedList<Position>();
    this.iterator().forEachRemaining(path::add);
    return path;
  }
}
