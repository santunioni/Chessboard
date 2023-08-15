package chess.board;

import chess.board.position.Position;
import chess.pieces.BoardPlacement;

public record InMemoryPositionBoardPlacement(Position position) implements BoardPlacement {

    public InMemoryPositionBoardPlacement(String position) {
        this(new Position(position));
    }

    public Position getPositionInBoard() {
        return this.position;
    }
}
