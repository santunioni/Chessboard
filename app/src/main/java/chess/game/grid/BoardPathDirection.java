package chess.game.grid;

import java.util.Set;

public enum BoardPathDirection {
    VERTICAL_UP, VERTICAL_DOWN, HORIZONTAL_LEFT, HORIZONTAL_RIGHT, DIAGONAL_UP_LEFT, DIAGONAL_UP_RIGHT, DIAGONAL_DOWN_LEFT, DIAGONAL_DOWN_RIGHT;

    public static Set<BoardPathDirection> allDirections() {
        return Set.of(
                BoardPathDirection.VERTICAL_UP,
                BoardPathDirection.VERTICAL_DOWN,
                BoardPathDirection.HORIZONTAL_LEFT,
                BoardPathDirection.HORIZONTAL_RIGHT,
                BoardPathDirection.DIAGONAL_UP_LEFT,
                BoardPathDirection.DIAGONAL_UP_RIGHT,
                BoardPathDirection.DIAGONAL_DOWN_LEFT,
                BoardPathDirection.DIAGONAL_DOWN_RIGHT
        );
    }

    public static Set<BoardPathDirection> diagonals() {
        return Set.of(
                BoardPathDirection.DIAGONAL_UP_LEFT,
                BoardPathDirection.DIAGONAL_UP_RIGHT,
                BoardPathDirection.DIAGONAL_DOWN_LEFT,
                BoardPathDirection.DIAGONAL_DOWN_RIGHT
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
}
