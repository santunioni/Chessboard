package chess.domain.move;

import chess.domain.board.ReadonlyBoard;
import chess.domain.grid.Direction;
import chess.domain.grid.Position;
import chess.domain.pieces.Color;
import chess.domain.pieces.PieceType;
import chess.domain.plays.Castle;
import chess.domain.plays.CastleSide;
import chess.domain.plays.Play;
import java.util.Set;

class KingMovePattern extends DirectionalMovePattern {
  private final Color color;

  public KingMovePattern(Color color) {
    super(Direction.allDirections(), color, PieceType.KING, 1);
    this.color = color;
  }

  public boolean couldMoveToIfEmpty(Position from, Position to, ReadonlyBoard board) {
    return super.couldMoveToIfEmpty(from, to, board) && from.stepsTo(to) == 1;
  }

  public Set<Play> getSuggestedPlays(Position from, ReadonlyBoard board) {
    final Set<Play> plays = super.getSuggestedPlays(from, board);
    plays.add(new Castle(this.color, CastleSide.KING_SIDE));
    plays.add(new Castle(this.color, CastleSide.QUEEN_SIDE));
    return plays;
  }
}
