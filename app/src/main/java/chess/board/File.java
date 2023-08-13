package chess.board;

/**
 * Represents a file on the chess board. (vertical columns)
 */
public enum File {
    A, B, C, D, E, F, G, H;

    static File fromString(String file) {
        return File.valueOf(file.toUpperCase());
    }

    public String toString() {
        return this.name().toLowerCase();
    }
}
