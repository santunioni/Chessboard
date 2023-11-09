package chess.domain.move;

import chess.domain.board.ReadonlyBoard;
import chess.domain.grid.Direction;
import chess.domain.grid.Path;
import chess.domain.grid.Position;
import chess.domain.pieces.Color;
import chess.domain.pieces.PieceType;
import chess.domain.plays.Capture;
import chess.domain.plays.Move;
import chess.domain.plays.Play;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

class DirectionalMovePattern implements MovePattern {
  private final Set<Direction> directions;
  private final PieceType type;
  private final Color color;
  private final Integer maxSteps;

  DirectionalMovePattern(Set<Direction> directions, Color color, PieceType type,
                         Integer maxSteps) {
    this.directions = directions;
    this.type = type;
    this.color = color;
    this.maxSteps = maxSteps;
  }

  DirectionalMovePattern(Set<Direction> directions, Color color, PieceType type) {
    this(directions, color, type, 8);
  }

  public boolean couldMoveToIfEmpty(Position from, Position to, ReadonlyBoard board) {
    Optional<Direction> direction = from.directionTo(to);
    if (direction.isEmpty() || !this.directions.contains(direction.get())) {
      return false;
    }
    final Path pathToTarget = new Path(from, direction.get(), from.stepsTo(to) - 1);
    return pathToTarget.isClearedOn(board);
  }

  public boolean threatens(Position from, Position to, ReadonlyBoard board) {
    return this.couldMoveToIfEmpty(from, to, board);
  }

  public Set<Play> suggestPlays(Position from, ReadonlyBoard board) {
    final Set<Play> plays = new HashSet<>();
    for (var direction : this.directions) {
      for (var position : new Path(from, direction, this.maxSteps)) {
        plays.add(new Move(this.type, this.color, from, position));
        plays.add(new Capture(this.type, this.color, from, position));
        if (board.getPieceAt(position).isPresent()) {
          break;
        }
      }
    }
    return plays;
  }
}
