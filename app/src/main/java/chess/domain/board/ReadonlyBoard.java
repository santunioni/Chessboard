package chess.domain.board;

import chess.domain.grid.Position;
import chess.domain.pieces.Color;
import chess.domain.pieces.Piece;
import chess.domain.pieces.PieceSpecification;
import chess.domain.plays.Play;
import java.util.Optional;
import java.util.stream.Stream;

public interface ReadonlyBoard {

  Optional<Piece> getPieceAt(Position position);

  default Optional<Piece> getPieceAt(String position) {
    return this.getPieceAt(new Position(position));
  }

  default Optional<Piece> getPieceAt(Position at, Color expectedColor) {
    return this.getPieceAt(at)
        .filter(piece -> piece.color().equals(expectedColor));
  }

  default Optional<Piece> getPieceAt(Position at, PieceSpecification spec) {
    return this.getPieceAt(at).filter(piece -> piece.getSpecification().equals(spec));
  }

  Stream<Piece> getPieces();

  default Stream<Piece> getPieces(Color color) {
    return this.getPieces().filter(p -> p.color().equals(color));
  }

  Position getPositionOf(Piece piece);

  default Optional<Piece> getSingleOf(PieceSpecification spec) {
    var pieces = this.getPieces().filter(p -> p.getSpecification().equals(spec)).toList();
    if (pieces.size() > 1) {
      throw new IllegalStateException("Many pieces found while requiring single.");
    }
    return Optional.ofNullable(pieces.size() == 1 ? pieces.get(0) : null);
  }

  Optional<Play> getLastPlay();

  Iterable<Play> history();

  default Color nextTurnPlayerColor() {
    return this.getLastPlay().map(play -> play.getPlayerColor().opposite()).orElse(Color.WHITE);
  }

  ReadonlyBoard simulate(Play play);
}
