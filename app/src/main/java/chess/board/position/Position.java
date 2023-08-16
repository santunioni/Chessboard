package chess.board.position;


import chess.board.path.BoardPathDirection;

import java.util.Optional;

public record Position(File file, Rank rank) {
    public Position(String position) {
        this(File.fromString(position.substring(0, 1)), Rank.fromString(position.substring(1, 2)));
    }

    @Override
    public String toString() {
        return file.toString() + rank.toString();
    }

    public Optional<BoardPathDirection> directionTo(Position that) {
        var fileDisplacement = this.file.distanceTo(that.file);
        var rankDisplacement = this.rank.distanceTo(that.rank);

        if (rankDisplacement.equals(0) && fileDisplacement.equals(0)) {
            return Optional.empty();
        }

        if (fileDisplacement.equals(0)) {
            return Optional.of(rankDisplacement > 0 ? BoardPathDirection.VERTICAL_UP : BoardPathDirection.VERTICAL_DOWN);
        }

        if (rankDisplacement.equals(0)) {
            return Optional.of(fileDisplacement > 0 ? BoardPathDirection.HORIZONTAL_RIGHT : BoardPathDirection.HORIZONTAL_LEFT);
        }

        if (fileDisplacement.equals(rankDisplacement)) {
            return Optional.of(fileDisplacement > 0 ? BoardPathDirection.DIAGONAL_UP_RIGHT : BoardPathDirection.DIAGONAL_DOWN_LEFT);
        }

        if (fileDisplacement.equals(-rankDisplacement)) {
            return Optional.of(fileDisplacement > 0 ? BoardPathDirection.DIAGONAL_DOWN_RIGHT : BoardPathDirection.DIAGONAL_UP_LEFT);
        }

        return Optional.empty();
    }
}
