package chess.board.path;

public enum BoardPathOrientation {
    VERTICAL_UP, VERTICAL_DOWN, HORIZONTAL_LEFT, HORIZONTAL_RIGHT, DIAGONAL_UP_LEFT, DIAGONAL_UP_RIGHT, DIAGONAL_DOWN_LEFT, DIAGONAL_DOWN_RIGHT;

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
