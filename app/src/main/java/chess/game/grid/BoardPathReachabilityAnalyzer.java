package chess.game.grid;

import chess.game.board.ReadonlyBoard;
import java.util.Set;

public class BoardPathReachabilityAnalyzer {

  private final ReadonlyBoard board;

  public BoardPathReachabilityAnalyzer(ReadonlyBoard board) {
    this.board = board;
  }

  public boolean isReachableWalkingInOneOfDirections(
      Position from,
      Set<Direction> allowedDirections,
      Position target
  ) {
    return this.isReachableWalkingInOneOfDirections(from, allowedDirections, target, 8);
  }

  public boolean isReachableWalkingInOneOfDirections(Position from,
                                                     Set<Direction> allowedDirections,
                                                     Position target, int maxSteps) {
    var direction = from.directionTo(target);

    if (direction.isEmpty() || !allowedDirections.contains(direction.get())) {
      return false;
    }

    var path = new Path(from, direction.get(), maxSteps);
    for (var step : path) {
      if (step.equals(target)) {
        return true;
      }
      if (this.board.getPieceAt(step).isPresent()) {
        break;
      }
    }

    return false;
  }
}
