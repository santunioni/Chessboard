package chess.game.board.pieces;

import chess.game.grid.Position;
import chess.game.plays.Capture;
import chess.game.plays.Move;
import chess.game.plays.Play;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class Knight extends Piece {

  /**
   * Constructor used only to delay refactoring the tests.
   *
   * @deprecated use {@link #Knight(Position, Color)} instead
   */
  public Knight(Color color) {
    super(new Position("a1"), color, PieceType.KNIGHT);
  }

  public Knight(Position initialPosition, Color color) {
    super(initialPosition, color, PieceType.KNIGHT);
  }

  public static List<Position> initialPositions(Color color) {
    return color.equals(Color.WHITE) ? List.of(new Position("b1"), new Position("g1")) :
        List.of(new Position("b8"), new Position("g8"));
  }

  public boolean couldMoveToIfEmpty(Position position) {
    var myPosition = this.currentPosition();
    var horizontalDistance = Math.abs(myPosition.file().distanceTo(position.file()));
    var verticalDistance = Math.abs(myPosition.rank().distanceTo(position.rank()));
    return (horizontalDistance == 1 && verticalDistance == 2)
        || (horizontalDistance == 2 && verticalDistance == 1);
  }

  protected Set<Play> getPossiblePlays() {
    var plays = new HashSet<Play>();

    for (var horizontalJump : List.of(-2, -1, 1, 2)) {
      for (var verticalJump : List.of(-2, -1, 1, 2)) {
        var targetPositionIndex =
            this.currentPosition().toIndex() + horizontalJump + 8 * verticalJump;
        if (0 <= targetPositionIndex && targetPositionIndex <= 63) {
          var targetPosition = Position.fromIndex(targetPositionIndex);
          if (this.couldMoveToIfEmpty(targetPosition)) {
            plays.add(
                new Move(this.getSpecification().color(), this.currentPosition(), targetPosition));
            plays.add(new Capture(this.getSpecification().color(), this.currentPosition(),
                targetPosition));
          }
        }
      }
    }

    return plays;
  }

  public Knight copy() {
    return new Knight(this.idInBoard(), this.getSpecification().color());
  }
}
