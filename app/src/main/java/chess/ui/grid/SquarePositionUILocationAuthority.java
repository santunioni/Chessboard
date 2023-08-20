package chess.ui.grid;

import chess.board.position.Position;

import java.awt.*;

public interface SquarePositionUILocationAuthority {
    Rectangle getRectangleForPosition(Position position, Double scale);

    default Rectangle getRectangleForPosition(Position position) {
        return this.getRectangleForPosition(position, 1.0);
    }

    Point getMiddlePointForPosition(Position position);
}
