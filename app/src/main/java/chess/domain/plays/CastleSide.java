package chess.domain.plays;

import chess.domain.grid.Direction;
import chess.domain.grid.File;

public enum CastleSide {
  KING_SIDE, QUEEN_SIDE;

  Direction toDirection() {
    return switch (this) {
      case KING_SIDE -> Direction.HORIZONTAL_RIGHT;
      case QUEEN_SIDE -> Direction.HORIZONTAL_LEFT;
    };
  }

  public File toRookFile() {
    return switch (this) {
      case KING_SIDE -> File.H;
      case QUEEN_SIDE -> File.A;
    };
  }
}
