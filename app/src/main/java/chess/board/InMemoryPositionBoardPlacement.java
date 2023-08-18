package chess.board;

import chess.board.position.Position;
import chess.pieces.Piece;

import java.util.Optional;

public class InMemoryPositionBoardPlacement implements BoardPlacement {

    private final Position position;
    private final BoardPieceAtPositionProvider board;

    public InMemoryPositionBoardPlacement(Position position, BoardPieceAtPositionProvider board) {
        this.position = position;
        this.board = board;
    }

    public Position getMyPosition() {
        return this.position;
    }

    public Optional<Piece> getPieceAt(Position position) {
        return this.board.getPieceAt(position);
    }
}
