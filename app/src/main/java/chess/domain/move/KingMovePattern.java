package chess.domain.move;

import chess.domain.board.PieceColor;
import chess.domain.board.PieceType;
import chess.domain.board.ReadonlyBoard;
import chess.domain.grid.Direction;
import chess.domain.grid.Position;
import chess.domain.play.Castle;
import chess.domain.play.CastleSide;
import chess.domain.play.Play;
import java.util.Set;

class KingMovePattern extends DirectionalMovePattern {
  private final PieceColor color;

  public KingMovePattern(PieceColor color) {
    super(Direction.allDirections(), color, PieceType.KING, 1);
    this.color = color;
  }

  public boolean couldMoveToIfEmpty(Position from, Position to, ReadonlyBoard board) {
    return super.couldMoveToIfEmpty(from, to, board) && from.stepsTo(to) == 1;
  }

  public Set<Play> suggestPlays(Position from, ReadonlyBoard board) {
    final Set<Play> plays = super.suggestPlays(from, board);
    plays.add(new Castle(this.color, CastleSide.KING_SIDE));
    plays.add(new Castle(this.color, CastleSide.QUEEN_SIDE));
    return plays;
  }
}
