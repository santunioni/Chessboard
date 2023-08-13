package chess.board;


/**
 * Represents the rank of a chess board. (horizontal rows)
 */
public enum Rank {
    ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT;

    private static final String possibleRanks = "12345678";

    static Rank fromString(String rank) {
        if (!possibleRanks.contains(rank)) {
            throw new IllegalArgumentException("Invalid rank: " + rank);
        }
        return Rank.values()[Integer.parseInt(rank) - 1];
    }

    public String toString() {
        return String.valueOf(this.ordinal() + 1);
    }
}
