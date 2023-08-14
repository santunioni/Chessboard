package chess.board.path;

import chess.board.position.File;
import chess.board.position.Position;
import chess.board.position.Rank;

import java.util.Iterator;
import java.util.Optional;

class BoardPathIterator implements Iterator<Position> {

    private final BoardPathOrientation orientation;
    private Position position;

    BoardPathIterator(Position position, BoardPathOrientation direction) {
        this.position = position;
        this.orientation = direction;
    }

    private Optional<File> nextFile() {
        var currentFile = this.position.file();
        if (this.orientation.isRight()) {
            return currentFile.next();
        }
        if (this.orientation.isLeft()) {
            return currentFile.previous();
        }
        return Optional.of(currentFile);
    }

    private Optional<Rank> nextRank() {
        var currentRank = this.position.rank();
        if (this.orientation.isUp()) {
            return currentRank.next();
        }
        if (this.orientation.isDown()) {
            return currentRank.previous();
        }
        return Optional.of(currentRank);
    }

    public boolean hasNext() {
        return this.nextFile().isPresent() && this.nextRank().isPresent();
    }

    public Position next() {
        this.position = new Position(this.nextFile().orElseThrow(), this.nextRank().orElseThrow());
        return this.position;
    }
}
