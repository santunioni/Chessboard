package chess.board.position;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
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

        this.getTopRightDiagonal().ifPresent(adjacentPositions::add);
        this.getTopLeftDiagonal().ifPresent(adjacentPositions::add);
        this.getBottomRightDiagonal().ifPresent(adjacentPositions::add);
        this.getBottomLeftDiagonal().ifPresent(adjacentPositions::add);

        this.getTopPosition().ifPresent(adjacentPositions::add);
        this.getBottomPosition().ifPresent(adjacentPositions::add);
        this.getLeftPosition().ifPresent(adjacentPositions::add);
        this.getRightPosition().ifPresent(adjacentPositions::add);

        return Collections.unmodifiableSet(adjacentPositions);
    }


    private Optional<Position> getTopRightDiagonal() {
        return this.file.next().flatMap(nextFile -> this.rank.next().map(nextRank -> new Position(nextFile, nextRank)));
    }

    private Optional<Position> getTopLeftDiagonal() {
        return this.file.previous().flatMap(previousFile -> this.rank.next().map(nextRank -> new Position(previousFile, nextRank)));
    }

    private Optional<Position> getBottomRightDiagonal() {
        return this.file.next().flatMap(nextFile -> this.rank.previous().map(previousRank -> new Position(nextFile, previousRank)));
    }

    private Optional<Position> getBottomLeftDiagonal() {
        return this.file.previous().flatMap(previousFile -> this.rank.previous().map(previousRank -> new Position(previousFile, previousRank)));
    }

    private Optional<Position> getTopPosition() {
        return this.rank.next().map(nextRank -> new Position(this.file, nextRank));
    }

    private Optional<Position> getBottomPosition() {
        return this.rank.previous().map(previousRank -> new Position(this.file, previousRank));
    }

    private Optional<Position> getLeftPosition() {
        return this.file.previous().map(previousFile -> new Position(previousFile, this.rank));
    }

    private Optional<Position> getRightPosition() {
        return this.file.next().map(nextFile -> new Position(nextFile, this.rank));
    }
}
