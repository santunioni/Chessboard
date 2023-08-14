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
        if (orientation == BoardPathOrientation.HORIZONTAL_RIGHT || this.orientation == BoardPathOrientation.DIAGONAL_UP_RIGHT || this.orientation == BoardPathOrientation.DIAGONAL_DOWN_RIGHT) {
            return currentFile.next();
        }
        if (orientation == BoardPathOrientation.HORIZONTAL_LEFT || this.orientation == BoardPathOrientation.DIAGONAL_UP_LEFT || this.orientation == BoardPathOrientation.DIAGONAL_DOWN_LEFT) {
            return currentFile.previous();
        }
        return Optional.of(currentFile);
    }

    private Optional<Rank> nextRank() {
        var currentRank = this.position.rank();
        if (orientation == BoardPathOrientation.VERTICAL_UP || this.orientation == BoardPathOrientation.DIAGONAL_UP_LEFT || this.orientation == BoardPathOrientation.DIAGONAL_UP_RIGHT) {
            return currentRank.next();
        }
        if (orientation == BoardPathOrientation.VERTICAL_DOWN || this.orientation == BoardPathOrientation.DIAGONAL_DOWN_LEFT || this.orientation == BoardPathOrientation.DIAGONAL_DOWN_RIGHT) {
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
