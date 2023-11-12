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

  public static PieceType fromAlgebraicNotationChar(String charLetter) {
    return switch (charLetter) {
      case "K" -> PieceType.KING;
      case "Q" -> PieceType.QUEEN;
      case "R" -> PieceType.ROOK;
      case "B" -> PieceType.BISHOP;
      case "N" -> PieceType.KNIGHT;
      default -> PieceType.PAWN;
    };
  }
}
