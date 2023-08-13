package chess.board;

public record Position(File file, Rank rank) {
    @Override
    public String toString() {
        return file.toString() + rank.toString();
    }
}
