package chess.game.board.pieces;

import static chess.game.plays.PlayFunctions.collectDirectionalPlays;

import chess.game.grid.Direction;
import chess.game.grid.Position;
import chess.game.plays.Castle;
import chess.game.plays.Play;
import java.util.HashSet;
import java.util.Set;

public class King extends Piece {

  public King(Color color) {
    super(color, PieceType.KING);
  }

  public static Position initialPosition(Color color) {
    return new Position(color == Color.WHITE ? "e1" : "e8");
  }

  public boolean couldMoveToIfEmpty(Position position) {
    return this.getMyPosition().isNeighborTo(position);
  }


  public King copy() {
    return new King(this.getSpecification().color());
  }

  protected Set<Play> getPossiblePlays() {
    var plays = new HashSet<Play>();
    collectDirectionalPlays(this, this.board, Direction.allDirections(), plays::add, 1);

    if (this.getSpecification().color() == Color.WHITE) {
      plays.add(new Castle(this.getSpecification().color(), new Position("a1")));
      plays.add(new Castle(this.getSpecification().color(), new Position("h1")));
    } else {
      plays.add(new Castle(this.getSpecification().color(), new Position("a8")));
      plays.add(new Castle(this.getSpecification().color(), new Position("h8")));
    }

    return plays;
  }
}
