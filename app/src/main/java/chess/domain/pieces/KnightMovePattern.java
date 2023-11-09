package chess.domain.pieces;

import chess.domain.grid.Position;
import chess.domain.plays.Capture;
import chess.domain.plays.Move;
import chess.domain.plays.Play;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class KnightMovePattern implements MovePattern {
  private final Piece piece;
  private final Color color;

  KnightMovePattern(Piece piece) {
    this.piece = piece;
    this.color = piece.color();
  }

  public boolean threatens(Position position) {
    return this.couldMoveToIfEmpty(position);
  }

  public boolean couldMoveToIfEmpty(Position position) {
    var myPosition = this.piece.currentPosition();
    var horizontalDistance = Math.abs(myPosition.file().distanceTo(position.file()));
    var verticalDistance = Math.abs(myPosition.rank().distanceTo(position.rank()));
    return (horizontalDistance == 1 && verticalDistance == 2)
        || (horizontalDistance == 2 && verticalDistance == 1);
  }

  public Set<Play> getSuggestedPlays() {
    var plays = new HashSet<Play>();

    for (var horizontalJump : List.of(-2, -1, 1, 2)) {
      for (var verticalJump : List.of(-2, -1, 1, 2)) {
        var targetPositionIndex =
            this.piece.currentPosition().toIndex() + horizontalJump + 8 * verticalJump;
        if (0 <= targetPositionIndex && targetPositionIndex <= 63) {
          var targetPosition = Position.fromIndex(targetPositionIndex);
          if (this.couldMoveToIfEmpty(targetPosition)) {
            plays.add(new Move(PieceType.KNIGHT, this.color, this.piece.currentPosition(),
                targetPosition));
            plays.add(
                new Capture(PieceType.KNIGHT, this.color, this.piece.currentPosition(),
                    targetPosition));
          }
        }
      }
    }

    return plays;
  }
}
