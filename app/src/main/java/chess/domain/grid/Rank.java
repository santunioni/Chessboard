package chess.domain.grid;


import java.util.Optional;

/**
 * Represents the rank of a chess board. (horizontal rows)
 */
public enum Rank {
  ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT;


  static Rank fromString(String rank) {
    var rankE = createFromIndex(Integer.parseInt(rank) - 1);
    if (rankE.isEmpty()) {
      throw new IllegalArgumentException("Invalid rank: " + rank);
    }
    return rankE.get();
  }

  static Optional<Rank> createFromIndex(int index) {
    var values = Rank.values();
    if (index < 0 || index >= values.length) {
      return Optional.empty();
    }
    return Optional.of(values[index]);
  }

  public String toString() {
    return String.valueOf(this.ordinal() + 1);
  }

  public Optional<Rank> next() {
    return createFromIndex(this.ordinal() + 1);
  }

  public Optional<Rank> previous() {
    return createFromIndex(this.ordinal() - 1);
  }

  public Integer distanceTo(Rank that) {
    return that.ordinal() - this.ordinal();
  }
}
