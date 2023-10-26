package chess.domain.pieces;

import chess.domain.grid.Direction;
import chess.domain.grid.File;
import chess.domain.grid.Path;
import chess.domain.grid.Position;
import chess.domain.grid.Rank;
import chess.domain.plays.Capture;
import chess.domain.plays.EnPassant;
import chess.domain.plays.Move;
import chess.domain.plays.Play;
import chess.domain.plays.Promotion;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class PawnMovePattern implements MovePattern {
  private final Piece piece;
  private final Direction walkDirection;

  public PawnMovePattern(Piece piece) {
    this.piece = piece;
    this.walkDirection = walkDirectionFor(this.piece.color());
  }

  public static Direction walkDirectionFor(Color color) {
    return color == Color.WHITE ? Direction.VERTICAL_UP : Direction.VERTICAL_DOWN;
  }

  public static Rank getEnPassantRankFor(Color color) {
    return color == Color.WHITE ? Rank.FIVE : Rank.FOUR;
  }

  public static Rank getStartRankFor(Color color) {
    return color == Color.WHITE ? Rank.TWO : Rank.SEVEN;
  }

  public static Rank getPromotionRankFor(Color color) {
    return getStartRankFor(color.opposite());
  }

  private boolean hasAlreadyMoved() {
    return this.piece.currentPosition().rank() != getStartRankFor(this.piece.color());
  }

  public boolean couldMoveToIfEmpty(Position target) {
    var myPosition = this.piece.currentPosition();
    var stepsToTarget = myPosition.stepsTo(target);

    if (myPosition.directionTo(target).filter(d -> d.equals(this.walkDirection)).isEmpty()
        || (this.hasAlreadyMoved() && stepsToTarget > 1)) {
      return false;
    }

    return new Path(myPosition, this.walkDirection, stepsToTarget - 1).isClearOn(this.piece.board);
  }

  public boolean threatens(Position enemyPosition) {
    var myPosition = this.piece.currentPosition();

    if (Math.abs(myPosition.rank().distanceTo(enemyPosition.rank())) != 1
        || Math.abs(myPosition.file().distanceTo(enemyPosition.file())) != 1) {
      return false;
    }

    return myPosition.rank().distanceTo(enemyPosition.rank())
        == (this.walkDirection == Direction.VERTICAL_UP ? 1 : -1);
  }

  public Set<Play> getSuggestedPlays() {
    var plays = new HashSet<Play>();

    var from = this.piece.currentPosition();
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

        if (this.couldMoveToIfEmpty(target)) {
          if (target.rank() == getPromotionRankFor(this.piece.color())) {
            Promotion.possibleTypes.forEach(type -> plays.add(
                new Promotion(new Move(PieceType.PAWN, this.piece.color(), from, target), type)));
          } else {
            plays.add(new Move(PieceType.PAWN, this.piece.color(), from, target));
          }
        }

        if (this.threatens(target)) {
          if (target.rank() == getPromotionRankFor(this.piece.color())) {
            Promotion.possibleTypes.forEach(type -> plays.add(
                new Promotion(new Capture(PieceType.PAWN, this.piece.color(), from, target),
                    type)));
          } else {
            plays.add(new Capture(PieceType.PAWN, this.piece.color(), from, target));
          }
          if (from.rank() == getEnPassantRankFor(this.piece.color())) {
            plays.add(new EnPassant(this.piece.color(), from, target));
          }
        }
      }
    }

    return plays;
  }
}
