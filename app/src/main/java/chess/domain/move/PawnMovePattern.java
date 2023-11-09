package chess.domain.move;

import chess.domain.board.ReadonlyBoard;
import chess.domain.grid.Direction;
import chess.domain.grid.File;
import chess.domain.grid.Path;
import chess.domain.grid.Position;
import chess.domain.grid.Rank;
import chess.domain.pieces.Color;
import chess.domain.pieces.PieceType;
import chess.domain.plays.Capture;
import chess.domain.plays.EnPassant;
import chess.domain.plays.Move;
import chess.domain.plays.Play;
import chess.domain.plays.Promotion;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class PawnMovePattern implements MovePattern {
  private final Color color;
  private final Direction walkDirection;

  public PawnMovePattern(Color color) {
    this.color = color;
    this.walkDirection = this.color.pawnWalkDirection();
  }

  public static Rank getPromotionRankFor(Color color) {
    return color.opposite().pawnStartRank();
  }

  private boolean isInitialPosition(Position from) {
    return from.rank() == this.color.pawnStartRank();
  }

  public boolean couldMoveToIfEmpty(Position from, Position to, ReadonlyBoard board) {
    var stepsToTarget = from.stepsTo(to);

    if (from.directionTo(to).filter(d -> d.equals(this.walkDirection)).isEmpty()
        || (!this.isInitialPosition(from) && stepsToTarget > 1)) {
      return false;
    }

    return new Path(from, this.walkDirection, stepsToTarget - 1)
        .isClearedOn(board);
  }

  public boolean threatens(Position from, Position to, ReadonlyBoard board) {
    if (Math.abs(from.rank().distanceTo(to.rank())) != 1
        || Math.abs(from.file().distanceTo(to.file())) != 1) {
      return false;
    }

    return from.rank().distanceTo(to.rank())
        == (this.walkDirection == Direction.VERTICAL_UP ? 1 : -1);
  }

  public Set<Play> suggestPlays(Position from, ReadonlyBoard board) {
    var plays = new HashSet<Play>();

    for (var verticalDisplacedPosition : new Path(from, this.walkDirection, 2)) {
      for (var horizontalDiff : List.of(-1, 0, 1)) {
        var fileOptional =
            File.createFromIndex(verticalDisplacedPosition.file().ordinal() + horizontalDiff);
        var targetOptional =
            fileOptional.map(f -> new Position(f, verticalDisplacedPosition.rank()));
        if (targetOptional.isEmpty()) {
          continue;
        }
        var target = targetOptional.get();

        if (this.couldMoveToIfEmpty(from, target, board)) {
          if (target.rank() == getPromotionRankFor(this.color)) {
            Promotion.possibleTypes.forEach(type -> plays.add(
                new Promotion(new Move(PieceType.PAWN, this.color, from, target), type)));
          } else {
            plays.add(new Move(PieceType.PAWN, this.color, from, target));
          }
        }

        if (this.threatens(from, target, board)) {
          if (target.rank() == getPromotionRankFor(this.color)) {
            Promotion.possibleTypes.forEach(type -> plays.add(
                new Promotion(new Capture(PieceType.PAWN, this.color, from, target),
                    type)));
          } else {
            plays.add(new Capture(PieceType.PAWN, this.color, from, target));
          }
          if (from.rank() == EnPassant.getEnPassantRankFor(this.color)) {
            plays.add(new EnPassant(this.color, from, target));
          }
        }
      }
    }

    return plays;
  }
}
