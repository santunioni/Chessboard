package chess.board;

import chess.board.position.Position;

public record InMemoryPositionBoardPlacement(Position position) implements BoardPlacement {

    public InMemoryPositionBoardPlacement(String position) {
        this(new Position(position));
    }

    public Position getPositionInBoard() {
        return this.position;
    }
}
