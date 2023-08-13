package chess.board;

/**
 * Represents the rank of a chess board. (horizontal rows)
 */
public enum Rank {
    ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT;

    public String toString() {
        return String.valueOf(this.ordinal() + 1);
    }
}
