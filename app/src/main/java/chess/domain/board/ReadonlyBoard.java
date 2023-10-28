package chess.domain.board;

import chess.domain.grid.Position;
import chess.domain.pieces.Color;
import chess.domain.pieces.Piece;
import chess.domain.pieces.PieceSpecification;
import chess.domain.pieces.PieceType;
import chess.domain.plays.Play;
import java.util.Optional;
import java.util.stream.Stream;

public interface ReadonlyBoard {

  Optional<Piece> getPieceAt(Position position);

  default Optional<Piece> getPieceAt(Position at, Color expectedColor) {
    return this.getPieceAt(at)
        .filter(piece -> piece.color().equals(expectedColor));
  }

  default Optional<Piece> getPieceAt(Position at, Color color, PieceType type) {
    return this.getPieceAt(at)
        .filter(piece -> piece.getSpecification().equals(new PieceSpecification(color, type)));
  }

  Stream<Piece> getPieces();

  default Stream<Piece> getPieces(Color color) {
    return this.getPieces().filter(p -> p.color().equals(color));
  }

  default Stream<Piece> getPieces(Color color, PieceType type) {
    return this.getPieces(color).filter(p -> p.type().equals(type));
  }

  Position getPositionOf(Piece piece);

  Optional<Play> getLastPlay();

  Iterable<Play> history();

  default Color nextTurnPlayerColor() {
    Optional<Play> lastPlayOptional = this.getLastPlay();
    if (lastPlayOptional.isPresent()) {
      return lastPlayOptional.get().getPlayerColor().opposite();
    } else {
      return Color.WHITE;
    }
  }

  ReadonlyBoard simulate(Play play);
}
