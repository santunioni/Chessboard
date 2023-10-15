package chess.game.board;

import chess.game.board.pieces.Color;
import chess.game.board.pieces.Piece;
import chess.game.board.pieces.PieceSpecification;
import chess.game.grid.Position;
import chess.game.plays.validation.NoPieceAtPositionValidationError;
import chess.game.plays.validation.PlayValidationError;
import java.util.Iterator;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

public interface ReadonlyBoard {
  boolean equals(Object that);

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

  Stream<Map.Entry<Position, Piece>> getPieces();

  default Stream<Map.Entry<Position, Piece>> getPieces(Color color) {
    return this.getPieces().filter(p -> p.getValue().getSpecification().color().equals(color));
  }

  default Stream<Map.Entry<Position, Piece>> getPieces(PieceSpecification spec) {
    return this.getPieces().filter(p -> p.getValue().getSpecification().equals(spec));
  }

  default Position getPositionOf(Piece piece) {
    var piecesMatching = this.getPieces().filter(p -> p.getValue().equals(piece)).toList();
    if (piecesMatching.isEmpty()) {
      throw new RuntimeException("Piece not found in board");
    }
    if (piecesMatching.size() > 1) {
      throw new RuntimeException("Piece found more than once in board");
    }
    return piecesMatching.get(0).getKey();
  }

  default Optional<Piece> getSingleOf(PieceSpecification spec) {
    var pieces = this.getPieces(spec).toList();
    if (pieces.size() != 1) {
      return Optional.empty();
    }
    return Optional.of(pieces.get(0).getValue());
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
}
