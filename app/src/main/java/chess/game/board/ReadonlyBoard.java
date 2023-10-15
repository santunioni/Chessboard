package chess.game.board;

import chess.game.board.pieces.Piece;
import chess.game.grid.Position;
import java.util.Optional;

public interface ReadonlyBoard {
  Optional<Piece> getPieceAt(Position position);

  default Optional<Piece> getPieceAt(String position) {
    return this.getPieceAt(new Position(position));
  }

  default Boolean hasPieceAt(Position position) {
    return this.getPieceAt(position).isPresent();
  }
}
