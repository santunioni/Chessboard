package chess.game.board.pieces;

import static chess.game.plays.PlayFunctions.collectDirectionalPlays;

import chess.game.grid.Direction;
import chess.game.grid.Path;
import chess.game.grid.Position;
import chess.game.plays.Play;
import java.util.HashSet;
import java.util.Set;

public class Queen extends Piece {

  public Queen(Position initialPosition, Color color) {
    super(initialPosition, color, PieceType.QUEEN);
  }

  public static Position initialPositionFor(Color color) {
    return new Position(color == Color.WHITE ? "d1" : "d8");
  }

  public boolean couldMoveToIfEmpty(Position target) {
    var myPosition = this.currentPosition();
    return myPosition.directionTo(target).map(
        direction -> new Path(myPosition, direction, myPosition.stepsTo(target) - 1).isClearOn(
            this.board)).orElse(false);
  }

  public Queen copy() {
    return new Queen(this.idInBoard(), this.getSpecification().color());
  }

  protected Set<Play> getPossiblePlays() {
    var plays = new HashSet<Play>();
    collectDirectionalPlays(this, this.board, Direction.allDirections(), plays::add);
    return plays;
  }
}
