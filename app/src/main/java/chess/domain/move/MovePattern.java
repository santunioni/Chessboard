package chess.domain.move;

import chess.domain.board.ReadonlyBoard;
import chess.domain.grid.Position;
import chess.domain.plays.Play;
import java.util.Set;

public interface MovePattern {
  boolean couldMoveToIfEmpty(Position from, Position to, ReadonlyBoard board);

  boolean threatens(Position from, Position to, ReadonlyBoard board);

  Set<Play> getSuggestedPlays(Position from, ReadonlyBoard board);
}
