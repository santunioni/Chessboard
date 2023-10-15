package chess.game.board;

import chess.game.board.pieces.Color;
import chess.game.board.pieces.Piece;
import chess.game.board.pieces.PieceSpecification;
import chess.game.grid.Position;
import chess.game.plays.validation.NoPieceAtPositionValidationError;
import chess.game.plays.validation.PlayValidationError;
import java.util.Iterator;
import java.util.Optional;
import java.util.stream.Stream;

public interface ReadonlyBoard {
  Optional<Piece> getPieceAt(Position position);

  default Optional<Piece> getPieceAt(String position) {
    return this.getPieceAt(new Position(position));
  }

  default Piece getPieceAtOrThrown(Position at, Color expectedColor) throws PlayValidationError {
    return this.getPieceAt(at)
        .filter(piece -> piece.getSpecification().color().equals(expectedColor))
        .orElseThrow(() -> new NoPieceAtPositionValidationError(at));
  }

  default Piece getPieceAtOrThrown(Position at, PieceSpecification spec)
      throws PlayValidationError {
    return this.getPieceAt(at).filter(piece -> piece.getSpecification().equals(spec))
        .orElseThrow(() -> new NoPieceAtPositionValidationError(at));
  }

  default Boolean hasPieceAt(Position position) {
    return this.getPieceAt(position).isPresent();
  }

  Stream<Piece> getPieces();

  default Stream<Piece> getPieces(Color color) {
    return this.getPieces().filter(p -> p.getSpecification().color().equals(color));
  }

  default Stream<Piece> getPieces(PieceSpecification spec) {
    return this.getPieces().filter(p -> p.getSpecification().equals(spec));
  }

  default Optional<Piece> getSingleOf(PieceSpecification spec) {
    var pieces = this.getPieces(spec).toList();
    if (pieces.size() != 1) {
      return Optional.empty();
    }
    return Optional.of(pieces.get(0));
  }

  default boolean isPositionThreatenedBy(Position at, Color color) {
    for (Iterator<Piece> it = this.getPieces(color).iterator(); it.hasNext(); ) {
      var piece = it.next();
      if (piece.couldCaptureEnemyAt(at)) {
        return true;
      }
    }
    return false;
  }
}
