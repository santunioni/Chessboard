package chess.domain.move;

import chess.domain.grid.Direction;
import chess.domain.pieces.PieceSpecification;
import collections.DefaultHashMap;
import java.util.Map;
import java.util.Set;


public class MovePatternSelector {

  private static final Map<PieceSpecification, MovePattern> specificationToMovePatternSingleton =
      new DefaultHashMap<>(MovePatternSelector::createMovePatternForColorType);

  private static MovePattern createMovePatternForColorType(PieceSpecification spec) {
    return switch (spec.pieceType()) {
      case PAWN -> new PawnMovePattern(spec.color());
      case ROOK -> new DirectionalMovePattern(
          Set.of(Direction.VERTICAL_UP, Direction.VERTICAL_DOWN, Direction.HORIZONTAL_LEFT,
              Direction.HORIZONTAL_RIGHT), spec.color(), spec.pieceType());
      case KNIGHT -> new KnightMovePattern(spec.color());
      case BISHOP ->
          new DirectionalMovePattern(Direction.diagonals(), spec.color(), spec.pieceType());
      case QUEEN ->
          new DirectionalMovePattern(Direction.allDirections(), spec.color(), spec.pieceType());
      case KING -> new KingMovePattern(spec.color());
    };
  }

  public static MovePattern selectForPieceType(PieceSpecification specification) {
    return specificationToMovePatternSingleton.get(specification);
  }
}
