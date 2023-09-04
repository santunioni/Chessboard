package chess.game.grid;


import java.util.Iterator;
import java.util.Optional;

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
      public Iterator<Position> iterator() {
        return new Iterator<>() {
          private int nextposition = 0;

          public boolean hasNext() {
            return this.nextposition < 64;
          }

          public Position next() {
            var position = Position.fromIndex(this.nextposition);
            this.nextposition += 1;
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

  public Optional<BoardPathDirection> directionTo(Position that) {
    var fileDisplacement = this.file.distanceTo(that.file);
    var rankDisplacement = this.rank.distanceTo(that.rank);

    if (rankDisplacement.equals(0) && fileDisplacement.equals(0)) {
      return Optional.empty();
    }

    if (fileDisplacement.equals(0)) {
      return Optional.of(
          rankDisplacement > 0 ? BoardPathDirection.VERTICAL_UP : BoardPathDirection.VERTICAL_DOWN);
    }

    if (rankDisplacement.equals(0)) {
      return Optional.of(fileDisplacement > 0 ? BoardPathDirection.HORIZONTAL_RIGHT :
          BoardPathDirection.HORIZONTAL_LEFT);
    }

    if (fileDisplacement.equals(rankDisplacement)) {
      return Optional.of(fileDisplacement > 0 ? BoardPathDirection.DIAGONAL_UP_RIGHT :
          BoardPathDirection.DIAGONAL_DOWN_LEFT);
    }

    if (fileDisplacement.equals(-rankDisplacement)) {
      return Optional.of(fileDisplacement > 0 ? BoardPathDirection.DIAGONAL_DOWN_RIGHT :
          BoardPathDirection.DIAGONAL_UP_LEFT);
    }

    return Optional.empty();
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
