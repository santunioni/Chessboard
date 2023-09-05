package chess.game.plays;

import chess.game.grid.Position;
import chess.game.pieces.Color;
import chess.game.pieces.Type;
import chess.game.plays.validation.PlayValidationError;

public class LongAlgebraicNotationToPlayMapper {

  public static Play createPlayFromLongAlgebraicNotation(String algebraic)
      throws PlayValidationError {
    final char pieceTypeChar = algebraic.charAt(0);
    final Type pieceType = switch (pieceTypeChar) {
      case 'K', 'k' -> Type.KING;
      case 'Q', 'q' -> Type.QUEEN;
      case 'R', 'r' -> Type.ROOK;
      case 'B', 'b' -> Type.BISHOP;
      case 'N', 'n' -> Type.KNIGHT;
      case 'P', 'p' -> Type.PAWN;
      default -> throw new PlayValidationError("Unexpected value: " + algebraic);
    };
    final Color color = Character.isUpperCase(pieceTypeChar) ? Color.WHITE : Color.BLACK;

    final Position from = new Position(algebraic.substring(1, 3));
    final char move = algebraic.charAt(3);
    if (move != '-' && move != 'x') {
      throw new PlayValidationError("Expecting - or x");
    }
    final Position to = new Position(algebraic.substring(4, 6));

    if (move == 'x') {
      if (pieceType == Type.PAWN && algebraic.endsWith("e.p.")) {
        return new EnPassant(color, from, to);
      }
      return new Capture(color, from, to);
    }

    if (move == '-') {
      if (pieceType == Type.KING) {
        if (color == Color.WHITE) {
          if (from.equals(new Position("e1"))) {
            if (to.equals(new Position("g1"))) {
              return new Castle(color, new Position("h1"));
            }
            if (to.equals(new Position("c1"))) {
              return new Castle(color, new Position("a1"));
            }
          }
        } else {
          if (from.equals(new Position("e8"))) {
            if (to.equals(new Position("g8"))) {
              return new Castle(color, new Position("h8"));
            }
            if (to.equals(new Position("c8"))) {
              return new Castle(color, new Position("a8"));
            }
          }
        }
      }

      return new Move(color, from, to);
    }

    throw new PlayValidationError("PlayName not found");
  }
}
