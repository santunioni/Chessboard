package chess.ui.grid;


import chess.board.position.File;
import chess.board.position.Position;
import chess.board.position.Rank;

import javax.swing.*;
import java.awt.*;

public class SquareGridUI extends JPanel implements SquarePositionUILocationAuthority {

    public SquareGridUI() {
        super(new GridLayout(8, 8));
        for (var rank : Rank.values()) {
            for (var file : File.values()) {
                var position = new Position(file, rank);
                var square = new SquareUI(position);
                this.add(square);
            }
        }
    }


    public void resizeTo(Integer size) {
        var dimension = new Dimension(size, size);
        this.setMinimumSize(dimension);
        this.setPreferredSize(dimension);
        this.setMaximumSize(dimension);
        this.setBounds(0, 0, size, size);
    }

    private Integer getBoardSize() {
        return this.getWidth();
    }

    private Integer getSquareSize() {
        return this.getBoardSize() / 8;
    }

    public Rectangle getRectangleForPosition(Position position, Double ratio) {
        Point middlePoint = this.getMiddlePointForPosition(position);
        var size = this.getSquareSize() * ratio;
        return new Rectangle(
                (int) Math.round(middlePoint.x - size / 2),
                (int) Math.round(middlePoint.y - size / 2),
                (int) Math.round(size),
                (int) Math.round(size)
        );
    }

    public Point getMiddlePointForPosition(Position position) {
        int width = this.getSquareSize();
        int height = this.getSquareSize();

        var x = position.file().ordinal() * width + width / 2;
        var y = this.getBoardSize() - (position.rank().ordinal() * height + height / 2);

        return new Point(x, y);
    }
}
