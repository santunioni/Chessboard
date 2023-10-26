package chess.domain.pieces;

import chess.domain.grid.Direction;
import chess.domain.grid.Position;
import chess.domain.plays.Castle;
import chess.domain.plays.CastleSide;
import chess.domain.plays.Play;
import java.util.Set;

class KingMovePattern extends DirectionalMovePattern {
  private final Piece piece;

  public KingMovePattern(Piece piece) {
    super(Direction.allDirections(), piece, 1);
    this.piece = piece;
  }

  public boolean couldMoveToIfEmpty(Position target) {
    return super.couldMoveToIfEmpty(target) && this.piece.currentPosition().stepsTo(target) == 1;
  }

  public Set<Play> getSuggestedPlays() {
    final Set<Play> plays = super.getSuggestedPlays();
    plays.add(new Castle(this.piece.color(), CastleSide.KING_SIDE));
    plays.add(new Castle(this.piece.color(), CastleSide.QUEEN_SIDE));
    return plays;
  }
}
