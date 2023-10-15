package chess.game.board;

import chess.game.board.pieces.Color;
import chess.game.board.pieces.Piece;
import chess.game.board.pieces.PieceSpecification;
import chess.game.board.pieces.PieceType;
import chess.game.grid.Position;
import chess.game.plays.validation.NoPieceAtPositionValidationError;
import chess.game.plays.validation.PieceAtPositionIsOfUnexpectedColorValidationError;
import chess.game.plays.validation.PlayValidationError;
import java.util.List;
import java.util.Optional;

public interface ReadonlyBoard {
  Optional<Piece> getPieceAt(Position position);

  default Optional<Piece> getPieceAt(String position) {
    return this.getPieceAt(new Position(position));
  }

  List<Piece> getPiecesOf(Color player);

  default List<Piece> getPiecesOf(PieceSpecification spec) {
    return this.getPiecesOf(spec.color()).stream()
        .filter(piece -> piece.getSpecification().equals(spec)).toList();
  }

  default Optional<Piece> getSingleOf(PieceSpecification spec) {
    var pieces = this.getPiecesOf(spec);
    if (pieces.size() != 1) {
      return Optional.empty();
    }
    return Optional.of(pieces.get(0));
  }

  default Piece getPieceAtOrThrown(Position at, Color expectedColor) throws PlayValidationError {
    var pieceOptional = this.getPieceAt(at);
    if (pieceOptional.isEmpty()) {
      throw new NoPieceAtPositionValidationError(at);
    }

    var piece = pieceOptional.get();
    if (piece.getSpecification().color() != expectedColor) {
      throw new PieceAtPositionIsOfUnexpectedColorValidationError(at, expectedColor);
    }

    return piece;
  }

  default Piece getPawnOrThrown(Position at, Color expectedColor) throws PlayValidationError {
    var piece = this.getPieceAtOrThrown(at, expectedColor);

    if (piece.getSpecification().pieceType() != PieceType.PAWN) {
      throw new PlayValidationError("Piece is not a pawn");
    }

    return piece;
  }

  default boolean isPositionThreatenedBy(Position at, Color color) {
    for (var piece : this.getPiecesOf(color)) {
      if (piece.couldCaptureEnemyAt(at)) {
        return true;
      }
    }
    return false;
  }

  default Boolean hasPieceAt(Position position) {
    return this.getPieceAt(position).isPresent();
  }
}
