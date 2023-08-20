package chess.ui.grid;

import chess.board.position.Position;

import java.awt.*;

public class PositionRectangle extends Rectangle {

    public PositionRectangle(Position position) {
        this(new PositionLocation(position));
    }

    public PositionRectangle(PositionLocation positionLocation) {
        super(positionLocation.x, positionLocation.y, PositionLocation.SQUARE_SIZE, PositionLocation.SQUARE_SIZE);
    }

    private PositionRectangle(Rectangle rectangle) {
        super(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
    }

    public PositionRectangle rescale(java.lang.Double ratio) {
        var newWidth = this.width * ratio;
        var newHeight = this.height * ratio;
        var newX = this.x + (this.width - newWidth) / 2;
        var newY = this.y + (this.height - newHeight) / 2;
        return new PositionRectangle(new Rectangle((int) newX, (int) newY, (int) newWidth, (int) newHeight));
    }

    public Dimension toDimension() {
        return new Dimension(this.width, this.height);
    }
}
