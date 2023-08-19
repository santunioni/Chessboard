package chess;

import chess.board.position.Position;

import javax.swing.*;
import java.awt.*;

public class SquareUIJButton extends JButton {
    private static final Color lightSquareColor = new Color(240, 217, 181); // Light square color
    private static final Color darkSquareColor = new Color(181, 136, 99);   // Dark square color

    public SquareUIJButton(Position position) {
        this.setOpaque(true);
        this.setBackground((position.rank().ordinal() + position.file().ordinal()) % 2 == 0 ? SquareUIJButton.darkSquareColor : SquareUIJButton.lightSquareColor);
        this.setBorderPainted(false);
    }
}
