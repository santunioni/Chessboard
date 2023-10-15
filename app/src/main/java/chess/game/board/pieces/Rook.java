package chess.game.board.pieces;

import static chess.game.plays.PlayFunctions.collectDirectionalPlays;

import chess.game.grid.BoardPathReachabilityAnalyzer;
import chess.game.grid.Direction;
import chess.game.grid.Position;
import chess.game.plays.Play;
import java.util.HashSet;
import java.util.Set;

public class Rook extends Piece {
  private static final Set<Direction> pathDirections = Set.of(
      Direction.VERTICAL_UP,
      Direction.VERTICAL_DOWN,
      Direction.HORIZONTAL_LEFT,
      Direction.HORIZONTAL_RIGHT
  );

  public Rook(Color color) {
    super(color, PieceType.ROOK);
  }

  public boolean couldMoveToIfEmpty(Position enemyPosition) {
    return new BoardPathReachabilityAnalyzer(this.board).isReachableWalkingInOneOfDirections(
        this.board.getMyPosition(),
        Rook.pathDirections,
        enemyPosition
    );
  }


  public Rook copy() {
    return new Rook(this.getSpecification().color());
  }

  protected Set<Play> getPossiblePlays() {
    var plays = new HashSet<Play>();
    collectDirectionalPlays(this, this.board, Rook.pathDirections, plays::add);
    return plays;
  }
}
