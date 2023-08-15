package chess.board;

import chess.board.position.Position;
import chess.pieces.Piece;

import java.util.Optional;

public interface BoardPieceAtPositionProvider {
    public Optional<Piece> getPieceAt(Position position);

    default public Optional<Piece> getPieceAt(String position) {
        return this.getPieceAt(new Position(position));
    }
}
