package chess.domain.move;

import chess.domain.board.ReadonlyBoard;
import chess.domain.grid.Direction;
import chess.domain.pieces.Piece;
import java.util.Set;

public class MovePatternSelector {
  public static MovePattern selectMovePattern(Piece piece, ReadonlyBoard board) {
    return switch (piece.type()) {
      case PAWN -> new PawnMovePattern(piece, board);
      case ROOK -> new DirectionalMovePattern(
          Set.of(Direction.VERTICAL_UP, Direction.VERTICAL_DOWN, Direction.HORIZONTAL_LEFT,
              Direction.HORIZONTAL_RIGHT), piece, board);
      case KNIGHT -> new KnightMovePattern(piece);
      case BISHOP -> new DirectionalMovePattern(Direction.diagonals(), piece, board);
      case QUEEN -> new DirectionalMovePattern(Direction.allDirections(), piece, board);
      case KING -> new KingMovePattern(piece, board);
    };
  }
}
