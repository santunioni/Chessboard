package chess.ui.grid;

import chess.board.position.Position;

import java.awt.*;

public class PositionLocation extends Point {
    public static final int SQUARE_SIZE = 100;
    public static final int BOARD_SIZE = 8 * SQUARE_SIZE;

    PositionLocation(Position position) {
        super(position.file().ordinal() * SQUARE_SIZE, BOARD_SIZE - SQUARE_SIZE - position.rank().ordinal() * SQUARE_SIZE);
    }
}
