package chess.domain.pieces;

public enum PieceType {
  PAWN,
  ROOK,
  KNIGHT,
  BISHOP,
  QUEEN,
  KING;

  public String toStringAlgebraicNotation() {
    return switch (this) {
      case PAWN -> "";
      case ROOK -> "R";
      case KNIGHT -> "N";
      case BISHOP -> "B";
      case QUEEN -> "Q";
      case KING -> "K";
    };
  }
}
