package chess;

import chess.board.position.Position;

import java.awt.*;

public class PositionRectangle extends Rectangle {

    public static final int SQUARE_SIZE = 100;
    public static final int BOARD_SIZE = 8 * SQUARE_SIZE;


    PositionRectangle(Position position) {
        super(position.file().ordinal() * SQUARE_SIZE, BOARD_SIZE - SQUARE_SIZE - position.rank().ordinal() * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
    }

    private PositionRectangle(Rectangle rectangle) {
        super(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
    }

    public PositionRectangle rescale(java.lang.Double ratio) {
        var newWidth = this.width * ratio ;
        var newHeight = this.height * ratio ;
        var newX = this.x + (this.width - newWidth) / 2;
        var newY = this.y + (this.height - newHeight) / 2;
        return new PositionRectangle(new Rectangle((int) newX,(int) newY,(int) newWidth,(int) newHeight));
    }
}
