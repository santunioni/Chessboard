package chess.game.board.pieces;

import static chess.game.plays.PlayFunctions.collectDirectionalPlays;

import chess.game.grid.Direction;
import chess.game.grid.Path;
import chess.game.grid.Position;
import chess.game.plays.Play;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Rook extends Piece {
  private static final Set<Direction> pathDirections =
      Set.of(Direction.VERTICAL_UP, Direction.VERTICAL_DOWN, Direction.HORIZONTAL_LEFT,
          Direction.HORIZONTAL_RIGHT);

  public Rook(Position initialPosition, Color color) {
    super(initialPosition, color, PieceType.ROOK);
  }

  public static List<Position> initialPositionsFor(Color color) {
    return color.equals(Color.WHITE) ? List.of(new Position("a1"), new Position("h1")) :
        List.of(new Position("a8"), new Position("h8"));
  }

  public boolean couldMoveToIfEmpty(Position target) {
    var myPosition = this.currentPosition();
    return myPosition.directionTo(target).filter(Direction::isNotDiagonal).map(
        direction -> new Path(myPosition, direction, myPosition.stepsTo(target) - 1).isClearOn(
            this.board)).orElse(false);
  }


  public Rook copy() {
    return new Rook(this.idInBoard(), this.getSpecification().color());
  }

  protected Set<Play> getPossiblePlays() {
    var plays = new HashSet<Play>();
    collectDirectionalPlays(this, this.board, Rook.pathDirections, plays::add);
    return plays;
  }
}
