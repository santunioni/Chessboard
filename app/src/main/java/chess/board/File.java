package chess.board;

/**
 * Represents a file on the chess board. (vertical columns)
 */
public enum File {
    A, B, C, D, E, F, G, H;

    public String toString() {
        return this.name().toLowerCase();
    }
}
