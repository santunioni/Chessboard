package chess;

import chess.board.position.File;
import chess.board.position.Position;
import chess.board.position.Rank;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class GridUI extends JPanel {
    GridUI() {
        super(new GridLayout(8, 8));
        for (var rank : Arrays.stream(Rank.values()).sorted((a, b) -> b.ordinal() - a.ordinal()).toList()) {
            for (var file : File.values()) {
                var position = new Position(file, rank);
                var square = new SquareUI(position);
                square.addActionListener(e -> {
                    this.requestFocusInWindow();
                    this.click(position);
                });
                this.add(square);
            }
        }
    }

    private void click(Position position) {
        System.out.println("Clicked " + position);
    }
}
