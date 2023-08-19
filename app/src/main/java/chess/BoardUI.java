package chess;

import chess.board.position.File;
import chess.board.position.Position;
import chess.board.position.Rank;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.HashMap;

public class BoardUI extends JPanel {
    private final HashMap<Position, SquareUI> squares = new HashMap<>();

    BoardUI() {
        super(new GridLayout(8, 8));
        for (var rank : Arrays.stream(Rank.values()).sorted((a, b) -> b.ordinal() - a.ordinal()).toList()) {
            for (var file : File.values()) {
                var position = new Position(file, rank);
                var square = new SquareUI(position);
                square.addActionListener(e -> {
                    this.requestFocusInWindow();
                    this.click(position);
                });
                this.squares.put(position, square);
                this.add(square);
            }
        }
    }

    private void click(Position position) {
        var square = this.squares.get(position);
        System.out.println("Clicked " + square);
    }
}
