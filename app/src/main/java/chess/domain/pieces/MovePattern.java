package chess.domain.pieces;

import chess.domain.grid.Position;
import chess.domain.plays.Play;
import java.util.Set;

interface MovePattern {
  boolean couldMoveToIfEmpty(Position position);

  boolean threatens(Position position);

  Set<Play> getSuggestedPlays();
}
