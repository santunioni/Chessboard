package chess.domain.move;

import chess.domain.board.ReadonlyBoard;
import chess.domain.grid.Position;
import chess.domain.pieces.Color;
import chess.domain.pieces.PieceType;
import chess.domain.plays.Capture;
import chess.domain.plays.Move;
import chess.domain.plays.Play;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class KnightMovePattern implements MovePattern {
  private final Color color;

  KnightMovePattern(Color color) {
    this.color = color;
  }

  public boolean threatens(Position from, Position to, ReadonlyBoard board) {
    return this.couldMoveToIfEmpty(from, to, board);
  }

  public boolean couldMoveToIfEmpty(Position from, Position to, ReadonlyBoard board) {
    var horizontalDistance = Math.abs(from.file().distanceTo(to.file()));
    var verticalDistance = Math.abs(from.rank().distanceTo(to.rank()));
    return (horizontalDistance == 1 && verticalDistance == 2)
        || (horizontalDistance == 2 && verticalDistance == 1);
  }

  public Set<Play> getSuggestedPlays(Position from, ReadonlyBoard board) {
    var plays = new HashSet<Play>();

    for (var horizontalJump : List.of(-2, -1, 1, 2)) {
      for (var verticalJump : List.of(-2, -1, 1, 2)) {
        var targetPositionIndex =
            from.toIndex() + horizontalJump + 8 * verticalJump;
        if (0 <= targetPositionIndex && targetPositionIndex <= 63) {
          var targetPosition = Position.fromIndex(targetPositionIndex);
          if (this.couldMoveToIfEmpty(from, targetPosition, board)) {
            plays.add(new Move(PieceType.KNIGHT, this.color, from,
                targetPosition));
            plays.add(
                new Capture(PieceType.KNIGHT, this.color, from,
                    targetPosition));
          }
        }
      }
    }

    return plays;
  }
}
