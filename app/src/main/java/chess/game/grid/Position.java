package chess.game.grid;


import java.util.Iterator;
import java.util.Optional;
import javax.annotation.Nonnull;

public record Position(File file, Rank rank) {
  public Position(String position) {
    this(File.fromString(position.substring(0, 1)), Rank.fromString(position.substring(1, 2)));
  }

  public static Position fromIndex(int index) {
    return new Position(File.createFromIndex(index % 8).orElseThrow(),
        Rank.createFromIndex(index / 8).orElseThrow());
  }

  public static Iterable<Position> values() {
    return new Iterable<>() {
      @Nonnull
      public Iterator<Position> iterator() {
        return new Iterator<>() {
          private int nextPosition = 0;

          public boolean hasNext() {
            return this.nextPosition < 64;
          }

          public Position next() {
            var position = Position.fromIndex(this.nextPosition);
            this.nextPosition += 1;
            return position;
          }
        };
      }
    };
  }

  public int toIndex() {
    return this.rank.ordinal() * 8 + this.file.ordinal();
  }

  @Override
  public String toString() {
    return file.toString() + rank.toString();
  }

  public Optional<Direction> directionTo(Position that) {
    var fileDisplacement = this.file.distanceTo(that.file);
    var rankDisplacement = this.rank.distanceTo(that.rank);

    if (rankDisplacement.equals(0) && fileDisplacement.equals(0)) {
      return Optional.empty();
    }

    if (fileDisplacement.equals(0)) {
      return Optional.of(
          rankDisplacement > 0 ? Direction.VERTICAL_UP : Direction.VERTICAL_DOWN);
    }

    if (rankDisplacement.equals(0)) {
      return Optional.of(fileDisplacement > 0 ? Direction.HORIZONTAL_RIGHT :
          Direction.HORIZONTAL_LEFT);
    }

    if (fileDisplacement.equals(rankDisplacement)) {
      return Optional.of(fileDisplacement > 0 ? Direction.DIAGONAL_UP_RIGHT :
          Direction.DIAGONAL_DOWN_LEFT);
    }

    if (fileDisplacement.equals(-rankDisplacement)) {
      return Optional.of(fileDisplacement > 0 ? Direction.DIAGONAL_DOWN_RIGHT :
          Direction.DIAGONAL_UP_LEFT);
    }

    return Optional.empty();
  }

  public int stepsTo(Position target) {
    return Math.max(Math.abs(this.file.distanceTo(target.file)),
        Math.abs(this.rank.distanceTo(target.rank)));
  }

  public Optional<Path> pathTo(Position target) {
    return this.directionTo(target)
        .map(direction -> new Path(this, direction, this.stepsTo(target)));
  }

  public Optional<Position> nextOn(Direction direction) {
    var nextFile = direction.isRight() ? this.file.next() :
        direction.isLeft() ? this.file.previous() : Optional.of(this.file);
    var nextRank = direction.isUp() ? this.rank.next() :
        direction.isDown() ? this.rank.previous() : Optional.of(this.rank);

    return nextFile.flatMap(file -> nextRank.map(rank -> new Position(file, rank)));
  }

  public Optional<Position> previousOn(Direction direction) {
    return this.nextOn(direction.opposite());
  }

  public boolean isNeighborTo(Position that) {
    var fileDisplacement = this.file.distanceTo(that.file);
    var rankDisplacement = this.rank.distanceTo(that.rank);

    if (rankDisplacement.equals(0) && fileDisplacement.equals(0)) {
      return false;
    }

    return Math.abs(fileDisplacement) < 2 && Math.abs(rankDisplacement) < 2;
  }
}
