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
    return new King(King.initialPositionFor(color), color);
  }

  public Piece createQueen(Color color) {
    return new Queen(Queen.initialPositionFor(color), color);
  }

  public List<Piece> createRooks(Color color) {
    var rooks = new ArrayList<Piece>();
    Rook.initialPositionsFor(color).forEach(position -> rooks.add(new Rook(position, color)));
    return rooks;
  }

  public List<Piece> createKnights(Color color) {
    var knights = new ArrayList<Piece>();
    Knight.initialPositionsFor(color).forEach(position -> knights.add(new Knight(position, color)));
    return knights;
  }

  public List<Piece> createBishops(Color color) {
    var bishops = new ArrayList<Piece>();
    Bishop.initialPositionsFor(color).forEach(position -> bishops.add(new Bishop(position, color)));
    return bishops;
  }

  public List<Piece> createPawns(Color color) {
    var pawns = new ArrayList<Piece>();
    Pawn.initialPositionsFor(color).forEach(position -> pawns.add(new Pawn(position, color)));
    return pawns;
  }
}
