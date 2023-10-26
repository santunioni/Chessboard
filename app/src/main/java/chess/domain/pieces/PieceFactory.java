package chess.domain.pieces;

import java.util.ArrayList;
import java.util.List;

public class PieceFactory {
  public List<Piece> createPiecesOf(Color color, PieceType type) {
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
    for (var color : Color.values()) {
      pieces.addAll(this.createPiecesOf(color, type));
    }
    return pieces;
  }

  public Piece createKing(Color color) {
    return new Piece(color.kingInitialPosition(), color, PieceType.KING);
  }

  public Piece createQueen(Color color) {
    return new Piece(color.queenInitialPosition(), color, PieceType.QUEEN);
  }

  public List<Piece> createRooks(Color color) {
    var rooks = new ArrayList<Piece>();
    color.rookInitialPositions()
        .forEach(position -> rooks.add(new Piece(position, color, PieceType.ROOK)));
    return rooks;
  }

  public List<Piece> createKnights(Color color) {
    var knights = new ArrayList<Piece>();
    color.knightInitialPositions()
        .forEach(position -> knights.add(new Piece(position, color, PieceType.KNIGHT)));
    return knights;
  }

  public List<Piece> createBishops(Color color) {
    var bishops = new ArrayList<Piece>();
    color.bishopInitialPositions()
        .forEach(position -> bishops.add(new Piece(position, color, PieceType.BISHOP)));
    return bishops;
  }

  public List<Piece> createPawns(Color color) {
    var pawns = new ArrayList<Piece>();
    color.pawnInitialPositions()
        .forEach(position -> pawns.add(new Piece(position, color, PieceType.PAWN)));
    return pawns;
  }
}
