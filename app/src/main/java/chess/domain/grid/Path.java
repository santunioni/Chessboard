package chess.domain.grid;

import chess.domain.board.PieceColor;
import chess.domain.board.ReadonlyBoard;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import javax.annotation.Nonnull;


public class Path implements Iterable<Position> {

  private final Position from;
  private final Direction direction;
  private final int maxSteps;

  public Path(Position from, Direction direction, int maxSteps) {
    this.from = from;
    this.direction = direction;
    this.maxSteps = maxSteps;
  }

  public Path(Position from, Direction direction) {
    this(from, direction, 8);
  }

  public Direction getDirection() {
    return direction;
  }

  @Nonnull
  public Iterator<Position> iterator() {
    return new Iterator<>() {
      private Position position = Path.this.from;
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

  public Boolean isBlockedOn(ReadonlyBoard board) {
    for (var position : this) {
      if (board.getPieceAt(position).isPresent()) {
        return true;
      }
    }
    return false;
  }

  public Boolean isClearedOn(ReadonlyBoard board) {
    return !this.isBlockedOn(board);
  }

  public Boolean isThreatenedBy(PieceColor enemyColor, ReadonlyBoard board) {
    for (var it = board.getPieces(enemyColor).iterator(); it.hasNext(); ) {
      var enemy = it.next();
      for (var step : this) {
        if (enemy.threatens(step)) {
          return true;
        }
      }
    }
    return false;
  }

  public List<Position> toPositionList() {
    var path = new LinkedList<Position>();
    this.iterator().forEachRemaining(path::add);
    return path;
  }
}
