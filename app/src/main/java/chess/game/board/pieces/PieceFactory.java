package chess.game.board.pieces;

import chess.game.grid.File;
import chess.game.grid.Position;
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
    return new King(King.initialPosition(color), color);
  }

  public Piece createQueen(Color color) {
    return new Queen(Queen.initialPosition(color), color);
  }

  public List<Piece> createRooks(Color color) {
    var rooks = new ArrayList<Piece>();
    for (var initialPosition : color == Color.WHITE ? List.of("a1", "h1") : List.of("a8", "h8")) {
      rooks.add(new Rook(new Position(initialPosition), color));
    }
    return rooks;
  }

  public List<Piece> createKnights(Color color) {
    var knights = new ArrayList<Piece>();
    for (var initialPosition : color == Color.WHITE ? List.of("b1", "g1") : List.of("b8", "g8")) {
      knights.add(new Knight(new Position(initialPosition), color));
    }
    return knights;
  }

  public List<Piece> createBishops(Color color) {
    var bishops = new ArrayList<Piece>();
    for (var initialPosition : color == Color.WHITE ? List.of("c1", "f1") : List.of("c8", "f8")) {
      bishops.add(new Bishop(new Position(initialPosition), color));
    }
    return bishops;
  }

  public List<Piece> createPawns(Color color) {
    var rank = Pawn.getStartRank(color);
    var pawns = new ArrayList<Piece>();
    for (var file : File.values()) {
      pawns.add(new Pawn(new Position(file, rank), color));
    }
    return pawns;
  }
}
