package chess.board.position;


public record Position(File file, Rank rank) {
    public Position(String position) {
        this(File.fromString(position.substring(0, 1)), Rank.fromString(position.substring(1, 2)));
    }

    @Override
    public String toString() {
        return file.toString() + rank.toString();
    }

}
