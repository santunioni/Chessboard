package chess.game.board.pieces;

import java.util.ArrayList;
import java.util.List;

public class PieceFactory {
  public List<Piece> createPiecesOf(PieceSpecification pieceSpecification) {
    return switch (pieceSpecification.pieceType()) {
      case KING -> List.of(this.createKing(pieceSpecification.color()));
      case QUEEN -> List.of(this.createQueen(pieceSpecification.color()));
      case ROOK -> this.createRooks(pieceSpecification.color());
      case KNIGHT -> this.createKnights(pieceSpecification.color());
      case BISHOP -> this.createBishops(pieceSpecification.color());
      case PAWN -> this.createPawns(pieceSpecification.color());
    };
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
