package chess.board;

import chess.board.position.Position;
import chess.pieces.Piece;

import java.util.Optional;

public class InMemoryPositionBoardPlacement implements BoardPlacement, BoardPieceAtPositionProvider {

    private final Position position;
    public InMemoryPositionBoardPlacement(Position position) {
        this.position = position;
    }

    public InMemoryPositionBoardPlacement(String position) {
        this(new Position(position));
    }

    public Position getMyPosition() {
        return this.position;
    }

    @Override
    public Optional<Piece> getPieceAt(Position position) {
        return Optional.empty();
    }
}
