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
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Pawn extends Piece {

  private final Direction walkDirection = Pawn.walkDirectionFor(this.color());

  public Pawn(Position initialPosition, Color color) {
    super(initialPosition, color, PieceType.PAWN);
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

  public static List<Position> initialPositionsFor(Color color) {
    var positions = new ArrayList<Position>();
    var rank = Pawn.getStartRankFor(color);
    for (var file : File.values()) {
      positions.add(new Position(file, rank));
    }
    return positions;
  }

  private boolean hasAlreadyMoved() {
    return this.currentPosition().rank() != getStartRankFor(this.color());
  }

  public boolean threatens(Position enemyPosition) {
    var myPosition = this.currentPosition();

    if (Math.abs(myPosition.rank().distanceTo(enemyPosition.rank())) != 1
        || Math.abs(myPosition.file().distanceTo(enemyPosition.file())) != 1) {
      return false;
    }

    return myPosition.rank().distanceTo(enemyPosition.rank())
        == (this.walkDirection == Direction.VERTICAL_UP ? 1 : -1);
  }

  public boolean couldMoveToIfEmpty(Position target) {
    var myPosition = this.currentPosition();
    var stepsToTarget = myPosition.stepsTo(target);

    if (myPosition.directionTo(target).filter(d -> d.equals(this.walkDirection)).isEmpty()
        || (this.hasAlreadyMoved() && stepsToTarget > 1)) {
      return false;
    }

    return new Path(myPosition, this.walkDirection,
        stepsToTarget - 1).isClearOn(this.board);
  }


  public Pawn copy() {
    return new Pawn(this.idInBoard(), this.color());
  }

  protected Set<Play> getSuggestedPlaysIncludingPossiblyInvalid() {
    var plays = new HashSet<Play>();

    var from = this.currentPosition();
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
          plays.add(new Move(this.color(), from, target));
        }

        if (this.threatens(target)) {
          plays.add(new Capture(this.color(), from, target));
          if (from.rank() == getEnPassantRankFor(this.color())) {
            plays.add(new EnPassant(this.color(), from, target));
          }
        }
      }
    }

    return plays;

  }
}
