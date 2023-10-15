package chess.game.board;

import chess.game.board.pieces.Color;
import chess.game.board.pieces.Piece;
import chess.game.board.pieces.PieceSpecification;
import chess.game.grid.Position;
import chess.game.plays.Play;
import java.util.Iterator;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

public interface ReadonlyBoard {

  Optional<Piece> getPieceAt(Position position);

  default Optional<Piece> getPieceAt(String position) {
    return this.getPieceAt(new Position(position));
  }

  default Optional<Piece> getPieceAt(Position at, Color expectedColor) {
    return this.getPieceAt(at)
        .filter(piece -> piece.getSpecification().color().equals(expectedColor));
  }

  default Optional<Piece> getPieceAt(Position at, PieceSpecification spec) {
    return this.getPieceAt(at).filter(piece -> piece.getSpecification().equals(spec));
  }

  default Boolean hasPieceAt(Position position) {
    return this.getPieceAt(position).isPresent();
  }

  Stream<Map.Entry<Position, Piece>> getPieces();

  default Stream<Map.Entry<Position, Piece>> getPieces(Color color) {
    return this.getPieces().filter(p -> p.getValue().getSpecification().color().equals(color));
  }

  default Stream<Map.Entry<Position, Piece>> getPieces(PieceSpecification spec) {
    return this.getPieces().filter(p -> p.getValue().getSpecification().equals(spec));
  }

  Position getPositionOf(Piece piece);

  default Optional<Piece> getSingleOf(PieceSpecification spec) {
    var pieces = this.getPieces(spec).toList();
    if (pieces.size() > 1) {
      throw new IllegalStateException("Many pieces found while requiring single.");
    }
    return Optional.ofNullable(pieces.size() == 1 ? pieces.get(0).getValue() : null);
  }

  default boolean isPositionThreatenedBy(Position at, Color color) {
    for (Iterator<Map.Entry<Position, Piece>> it = this.getPieces(color).iterator();
         it.hasNext(); ) {
      var piece = it.next().getValue();
      if (piece.couldCaptureEnemyAt(at)) {
        return true;
      }
    }
    return false;
  }

  Optional<Play> getLastPlay();

  Iterable<Play> history();

  default Color nextTurnPlayerColor() {
    return this.getLastPlay().map(play -> play.getPlayerColor().opposite()).orElse(Color.WHITE);
  }
}
