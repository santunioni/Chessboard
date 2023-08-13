package chess.board;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public record Position(File file, Rank rank) {
    public Position(String position) {
        this(File.fromString(position.substring(0, 1)), Rank.fromString(position.substring(1, 2)));
    }

    @Override
    public String toString() {
        return file.toString() + rank.toString();
    }


    public Set<Position> getAllAdjacentPositions() {
        var adjacentPositions = new HashSet<Position>();

        this.rank.previous().ifPresent(previousRank -> adjacentPositions.add(new Position(this.file, previousRank)));
        this.rank.next().ifPresent(nextRank -> adjacentPositions.add(new Position(this.file, nextRank)));

        this.file.previous().ifPresent(previousFile -> {
            adjacentPositions.add(new Position(previousFile, this.rank));
            this.rank.previous().ifPresent(previousRank -> adjacentPositions.add(new Position(previousFile, previousRank)));
            this.rank.next().ifPresent(nextRank -> adjacentPositions.add(new Position(previousFile, nextRank)));
        });

        this.file.next().ifPresent(nextFile -> {
            adjacentPositions.add(new Position(nextFile, this.rank));
            this.rank.previous().ifPresent(previousRank -> adjacentPositions.add(new Position(nextFile, previousRank)));
            this.rank.next().ifPresent(nextRank -> adjacentPositions.add(new Position(nextFile, nextRank)));
        });

        return Collections.unmodifiableSet(adjacentPositions);
    }
}
