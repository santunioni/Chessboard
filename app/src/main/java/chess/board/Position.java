package chess.board;

public record Position(File file, Rank rank) {
    @Override
    public String toString() {
        return file.toString() + rank.toString();
    }


    public boolean equals(Position that) {
        return this.file == that.file && this.rank == that.rank;
    }
}
