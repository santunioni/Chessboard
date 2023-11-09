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
  private final Integer maxSteps;

  DirectionalMovePattern(Set<Direction> directions, Piece piece, Integer maxSteps) {
    this.directions = directions;
    this.piece = piece;
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
        plays.add(new Move(piece.type(), piece.color(), this.piece.currentPosition(), position));
        plays.add(new Capture(piece.type(), piece.color(), this.piece.currentPosition(), position));
        if (this.piece.board.getPieceAt(position).isPresent()) {
          break;
        }
      }
    }
    return plays;
  }
}
