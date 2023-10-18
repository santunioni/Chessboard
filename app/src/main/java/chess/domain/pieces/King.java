package chess.domain.pieces;

import static chess.domain.plays.PlayFunctions.collectDirectionalPlays;

import chess.domain.grid.Direction;
import chess.domain.grid.Position;
import chess.domain.plays.Castle;
import chess.domain.plays.Play;
import java.util.HashSet;
import java.util.Set;

public class King extends Piece {

  public King(Position initialPosition, Color color) {
    super(initialPosition, color, PieceType.KING);
  }

  public static Position initialPositionFor(Color color) {
    return new Position(color == Color.WHITE ? "e1" : "e8");
  }

  public boolean couldMoveToIfEmpty(Position position) {
    return this.currentPosition().isNeighborTo(position);
  }

  public King copy() {
    return new King(this.idInBoard(), this.color());
  }

  protected Set<Play> getSuggestedPlaysIncludingPossiblyInvalid() {
    var plays = new HashSet<Play>();
    collectDirectionalPlays(this, this.board, Direction.allDirections(), plays::add, 1);

    if (this.color() == Color.WHITE) {
      plays.add(new Castle(this.color(), new Position("a1")));
      plays.add(new Castle(this.color(), new Position("h1")));
    } else {
      plays.add(new Castle(this.color(), new Position("a8")));
      plays.add(new Castle(this.color(), new Position("h8")));
    }

    return plays;
  }
}
