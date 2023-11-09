package chess.domain.move;

import chess.domain.grid.Direction;
import chess.domain.pieces.Color;
import chess.domain.pieces.PieceType;
import java.util.Set;

public class MovePatternSelector {
  public static MovePattern selectForPieceType(Color color, PieceType type) {
    return switch (type) {
      case PAWN -> new PawnMovePattern(color);
      case ROOK -> new DirectionalMovePattern(
          Set.of(Direction.VERTICAL_UP, Direction.VERTICAL_DOWN, Direction.HORIZONTAL_LEFT,
              Direction.HORIZONTAL_RIGHT), color, type);
      case KNIGHT -> new KnightMovePattern(color);
      case BISHOP -> new DirectionalMovePattern(Direction.diagonals(), color, type);
      case QUEEN -> new DirectionalMovePattern(Direction.allDirections(), color, type);
      case KING -> new KingMovePattern(color);
    };
  }
}
