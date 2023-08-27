package chess.game.board;

import chess.game.grid.Position;
import chess.game.pieces.Piece;

import java.util.Optional;

public interface BoardPieceAtPositionProvider {
    Optional<Piece> getPieceAt(Position position);

    default Optional<Piece> getPieceAt(String position) {
        return this.getPieceAt(new Position(position));
    }
}
