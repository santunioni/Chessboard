package chess.game.grid;

import java.util.Iterator;
import java.util.Optional;

public class BoardPathIterator implements Iterator<Position> {

    private final BoardPathDirection direction;
    private Position position;

    public BoardPathIterator(Position position, BoardPathDirection direction) {
        this.position = position;
        this.direction = direction;
    }

    private Optional<File> nextFile() {
        var currentFile = this.position.file();
        if (this.direction.isRight()) {
            return currentFile.next();
        }
        if (this.direction.isLeft()) {
            return currentFile.previous();
        }
        return Optional.of(currentFile);
    }

    private Optional<Rank> nextRank() {
        var currentRank = this.position.rank();
        if (this.direction.isUp()) {
            return currentRank.next();
        }
        if (this.direction.isDown()) {
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
