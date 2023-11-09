package chess.domain.move;

import chess.domain.grid.Position;
import chess.domain.plays.Play;
import java.util.Set;

public interface MovePattern {
  boolean couldMoveToIfEmpty(Position position);

  boolean threatens(Position position);

  Set<Play> getSuggestedPlays();
}
