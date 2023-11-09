package chess.domain.pieces;

import chess.domain.grid.Direction;
import chess.domain.grid.Position;
import chess.domain.plays.Castle;
import chess.domain.plays.CastleSide;
import chess.domain.plays.Play;
import java.util.Set;

class KingMovePattern extends DirectionalMovePattern {
  private final Piece piece;
  private final Color color;

  public KingMovePattern(Piece piece) {
    super(Direction.allDirections(), piece, 1);
    this.piece = piece;
    this.color = piece.color();
  }

  public boolean couldMoveToIfEmpty(Position target) {
    return super.couldMoveToIfEmpty(target) && this.piece.currentPosition().stepsTo(target) == 1;
  }

  public Set<Play> getSuggestedPlays() {
    final Set<Play> plays = super.getSuggestedPlays();
    plays.add(new Castle(this.color, CastleSide.KING_SIDE));
    plays.add(new Castle(this.color, CastleSide.QUEEN_SIDE));
    return plays;
  }
}
