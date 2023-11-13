package chess.domain.board;

import java.util.ArrayList;
import java.util.List;

public class PieceFactory {
  public List<Piece> createPiecesOf(PieceColor color, PieceType type) {
    return switch (type) {
      case KING -> List.of(this.createKing(color));
      case QUEEN -> List.of(this.createQueen(color));
      case ROOK -> this.createRooks(color);
      case KNIGHT -> this.createKnights(color);
      case BISHOP -> this.createBishops(color);
      case PAWN -> this.createPawns(color);
    };
  }

  public List<Piece> createPiecesOf(PieceType type) {
    var pieces = new ArrayList<Piece>();
    for (var color : PieceColor.values()) {
      pieces.addAll(this.createPiecesOf(color, type));
    }
    return pieces;
  }

  public Piece createKing(PieceColor color) {
    return new Piece(color.kingInitialPosition(), color, PieceType.KING);
  }

  public Piece createQueen(PieceColor color) {
    return new Piece(color.queenInitialPosition(), color, PieceType.QUEEN);
  }

  public List<Piece> createRooks(PieceColor color) {
    var rooks = new ArrayList<Piece>();
    color.rookInitialPositions()
        .forEach(position -> rooks.add(new Piece(position, color, PieceType.ROOK)));
    return rooks;
  }

  public List<Piece> createKnights(PieceColor color) {
    var knights = new ArrayList<Piece>();
    color.knightInitialPositions()
        .forEach(position -> knights.add(new Piece(position, color, PieceType.KNIGHT)));
    return knights;
  }

  public List<Piece> createBishops(PieceColor color) {
    var bishops = new ArrayList<Piece>();
    color.bishopInitialPositions()
        .forEach(position -> bishops.add(new Piece(position, color, PieceType.BISHOP)));
    return bishops;
  }

  public List<Piece> createPawns(PieceColor color) {
    var pawns = new ArrayList<Piece>();
    color.pawnInitialPositions()
        .forEach(position -> pawns.add(new Piece(position, color, PieceType.PAWN)));
    return pawns;
  }
}
