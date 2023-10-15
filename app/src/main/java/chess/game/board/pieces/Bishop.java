package chess.game.board.pieces;

import static chess.game.plays.PlayFunctions.collectDirectionalPlays;

import chess.game.grid.BoardPathReachabilityAnalyzer;
import chess.game.grid.Direction;
import chess.game.grid.Position;
import chess.game.plays.Play;
import java.util.HashSet;
import java.util.Set;

public class Bishop extends Piece {


  public Bishop(Color color) {
    super(color, PieceType.BISHOP);
  }

  public boolean couldMoveToIfEmpty(Position position) {
    return new BoardPathReachabilityAnalyzer(this.board).isReachableWalkingInOneOfDirections(
        this.board.getMyPosition(),
        Direction.diagonals(),
        position
    );
  }


  public Bishop copy() {
    return new Bishop(this.getSpecification().color());
  }

  protected Set<Play> getPossiblePlays() {
    var plays = new HashSet<Play>();
    collectDirectionalPlays(this, this.board, Direction.diagonals(), plays::add);
    return plays;
  }
}
