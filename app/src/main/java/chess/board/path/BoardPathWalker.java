package chess.board.path;

import chess.board.position.Position;

import java.util.Optional;

public record BoardPathWalker(Position position) {
    public Optional<BoardPathWalker> walk(Integer steps, BoardPathOrientation orientation) {
        var iterator = new BoardPathIterator(this.position, orientation);
        var position = this.position;
        for (int i = 0; i < steps; i++) {
            if (iterator.hasNext()) {
                position = iterator.next();
            } else {
                return Optional.empty();
            }
        }
        return Optional.of(new BoardPathWalker(position));
    }

    public Position getPosition() {
        return this.position;
    }
}
