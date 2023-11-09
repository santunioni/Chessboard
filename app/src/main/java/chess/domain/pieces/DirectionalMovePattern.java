package chess.domain.pieces;

import chess.domain.grid.Direction;
import chess.domain.grid.Path;
import chess.domain.grid.Position;
import chess.domain.plays.Capture;
import chess.domain.plays.Move;
import chess.domain.plays.Play;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

class DirectionalMovePattern implements MovePattern {
  private final Set<Direction> directions;
  private final Piece piece;
  private final PieceType type;
  private final Color color;
  private final Integer maxSteps;

  DirectionalMovePattern(Set<Direction> directions, Piece piece, Integer maxSteps) {
    this.directions = directions;
    this.piece = piece;
    this.type = piece.type();
    this.color = piece.color();
    this.maxSteps = maxSteps;
  }

  DirectionalMovePattern(Set<Direction> directions, Piece piece) {
    this(directions, piece, 8);
  }

  public boolean couldMoveToIfEmpty(Position target) {
    Position myPosition = this.piece.currentPosition();
    Optional<Direction> direction = myPosition.directionTo(target);
    if (direction.isEmpty() || !this.directions.contains(direction.get())) {
      return false;
    }
    final Path pathToTarget = new Path(myPosition, direction.get(), myPosition.stepsTo(target) - 1);
    return pathToTarget.isClearedOn(this.piece.board);
  }

  public boolean threatens(Position target) {
    return this.couldMoveToIfEmpty(target);
  }

  public Set<Play> getSuggestedPlays() {
    final Set<Play> plays = new HashSet<>();
    for (var direction : this.directions) {
      for (var position : new Path(this.piece.currentPosition(), direction, this.maxSteps)) {
        plays.add(new Move(this.type, this.color, this.piece.currentPosition(), position));
        plays.add(new Capture(this.type, this.color, this.piece.currentPosition(), position));
        if (this.piece.board.getPieceAt(position).isPresent()) {
          break;
        }
      }
    }
    return plays;
  }
}
