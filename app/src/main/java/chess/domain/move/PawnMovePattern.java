package chess.domain.move;

import chess.domain.board.ReadonlyBoard;
import chess.domain.grid.Direction;
import chess.domain.grid.File;
import chess.domain.grid.Path;
import chess.domain.grid.Position;
import chess.domain.grid.Rank;
import chess.domain.pieces.Color;
import chess.domain.pieces.Piece;
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
  private final Piece piece;
  private final ReadonlyBoard board;
  private final Direction walkDirection;

  public PawnMovePattern(Piece piece, ReadonlyBoard board) {
    this.piece = piece;
    this.color = piece.color();
    this.walkDirection = this.color.pawnWalkDirection();
    this.board = board;
  }

  public static Rank getPromotionRankFor(Color color) {
    return color.opposite().pawnStartRank();
  }

  private boolean hasAlreadyMoved() {
    return this.piece.currentPosition().rank() != this.color.pawnStartRank();
  }

  public boolean couldMoveToIfEmpty(Position target) {
    var myPosition = this.piece.currentPosition();
    var stepsToTarget = myPosition.stepsTo(target);

    if (myPosition.directionTo(target).filter(d -> d.equals(this.walkDirection)).isEmpty()
        || (this.hasAlreadyMoved() && stepsToTarget > 1)) {
      return false;
    }

    return new Path(myPosition, this.walkDirection, stepsToTarget - 1)
        .isClearedOn(this.board);
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
          if (target.rank() == getPromotionRankFor(this.color)) {
            Promotion.possibleTypes.forEach(type -> plays.add(
                new Promotion(new Move(PieceType.PAWN, this.color, from, target), type)));
          } else {
            plays.add(new Move(PieceType.PAWN, this.color, from, target));
          }
        }

        if (this.threatens(target)) {
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
