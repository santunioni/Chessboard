package chess.domain.grid;

import java.util.Set;

public enum Direction {
  VERTICAL_UP, VERTICAL_DOWN, HORIZONTAL_LEFT, HORIZONTAL_RIGHT, DIAGONAL_UP_LEFT,
  DIAGONAL_UP_RIGHT, DIAGONAL_DOWN_LEFT, DIAGONAL_DOWN_RIGHT;

  public static Set<Direction> allDirections() {
    return Set.of(
        Direction.VERTICAL_UP,
        Direction.VERTICAL_DOWN,
        Direction.HORIZONTAL_LEFT,
        Direction.HORIZONTAL_RIGHT,
        Direction.DIAGONAL_UP_LEFT,
        Direction.DIAGONAL_UP_RIGHT,
        Direction.DIAGONAL_DOWN_LEFT,
        Direction.DIAGONAL_DOWN_RIGHT
    );
  }

  public static Set<Direction> diagonals() {
    return Set.of(
        Direction.DIAGONAL_UP_LEFT,
        Direction.DIAGONAL_UP_RIGHT,
        Direction.DIAGONAL_DOWN_LEFT,
        Direction.DIAGONAL_DOWN_RIGHT
    );
  }

  public boolean isLeft() {
    return this == HORIZONTAL_LEFT || this == DIAGONAL_UP_LEFT || this == DIAGONAL_DOWN_LEFT;
  }

  public boolean isRight() {
    return this == HORIZONTAL_RIGHT || this == DIAGONAL_UP_RIGHT || this == DIAGONAL_DOWN_RIGHT;
  }

  public boolean isUp() {
    return this == VERTICAL_UP || this == DIAGONAL_UP_LEFT || this == DIAGONAL_UP_RIGHT;
  }

  public boolean isDown() {
    return this == VERTICAL_DOWN || this == DIAGONAL_DOWN_LEFT || this == DIAGONAL_DOWN_RIGHT;
  }

  public boolean isDiagonal() {
    return Direction.diagonals().contains(this);
  }

  public boolean isNotDiagonal() {
    return !this.isDiagonal();
  }

  public Direction opposite() {
    return switch (this) {
      case VERTICAL_UP -> VERTICAL_DOWN;
      case VERTICAL_DOWN -> VERTICAL_UP;
      case HORIZONTAL_LEFT -> HORIZONTAL_RIGHT;
      case HORIZONTAL_RIGHT -> HORIZONTAL_LEFT;
      case DIAGONAL_UP_LEFT -> DIAGONAL_DOWN_RIGHT;
      case DIAGONAL_UP_RIGHT -> DIAGONAL_DOWN_LEFT;
      case DIAGONAL_DOWN_LEFT -> DIAGONAL_UP_RIGHT;
      case DIAGONAL_DOWN_RIGHT -> DIAGONAL_UP_LEFT;
    };
  }
}
