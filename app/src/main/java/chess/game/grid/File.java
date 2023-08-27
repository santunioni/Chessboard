package chess.game.grid;

import java.util.Optional;

/**
 * Represents a file on the chess board. (vertical columns)
 */
public enum File {
    A, B, C, D, E, F, G, H;

    static File fromString(String file) {
        return File.valueOf(file.toUpperCase());
    }

    public static Optional<File> createFromIndex(int index) {
        var values = File.values();
        if (index < 0 || index >= values.length) {
            return Optional.empty();
        }
        return Optional.of(values[index]);
    }

    public String toString() {
        return this.name().toLowerCase();
    }

    public Optional<File> next() {
        return createFromIndex(this.ordinal() + 1);
    }

    public Optional<File> previous() {
        return createFromIndex(this.ordinal() - 1);
    }

    public Integer distanceTo(File that) {
        return that.ordinal() - this.ordinal();
    }
}
