package chess.board;

import chess.board.position.Position;
import chess.pieces.Piece;

import java.util.Optional;

public interface BoardPieceAtPositionProvider {
    Optional<Piece> getPieceAt(Position position);

    default Optional<Piece> getPieceAt(String position) {
        return this.getPieceAt(new Position(position));
    }
}
