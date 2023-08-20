package chess.ui.grid;


import chess.board.position.File;
import chess.board.position.Position;
import chess.board.position.Rank;

import javax.swing.*;
import java.awt.*;

public class SquareGridUI extends JPanel {

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

    public SquareGridUI resizeTo(Integer size) {
        var dimension = new Dimension(size, size);
        this.setMinimumSize(dimension);
        this.setPreferredSize(dimension);
        this.setMaximumSize(dimension);
        this.setBounds(0, 0, size, size);
        return this;
    }
}
