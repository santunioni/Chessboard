package chess.domain.pieces;

import static chess.domain.plays.PlayFunctions.collectDirectionalPlays;

import chess.domain.grid.Direction;
import chess.domain.grid.Path;
import chess.domain.grid.Position;
import chess.domain.plays.Play;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Bishop extends Piece {

  public Bishop(Position initialPosition, Color color) {
    super(initialPosition, color, PieceType.BISHOP);
  }

  public static List<Position> initialPositionsFor(Color color) {
    return color.equals(Color.WHITE) ? List.of(new Position("c1"), new Position("f1")) :
        List.of(new Position("c8"), new Position("f8"));
  }

  public boolean couldMoveToIfEmpty(Position target) {
    var myPosition = this.currentPosition();
    return myPosition.directionTo(target).filter(Direction::isDiagonal).map(
        direction -> new Path(myPosition, direction, myPosition.stepsTo(target) - 1).isClearOn(
            this.board)).orElse(false);
  }


  public Bishop copy() {
    return new Bishop(this.idInBoard(), this.color());
  }

  protected Set<Play> getSuggestedPlaysIncludingPossiblyInvalid() {
    var plays = new HashSet<Play>();
    collectDirectionalPlays(this, this.board, Direction.diagonals(), plays::add);
    return plays;
  }
}
