package chess.ui.pieces;

import chess.pieces.Color;
import chess.pieces.Type;

import javax.swing.*;
import java.awt.*;


public class PieceUI extends JLabel {


    PieceUI(Color color, Type type) {
        var icon = PieceIcon.getIcon(color, type);
        this.setIcon(icon);
        if (icon.getImageLoadStatus() != MediaTracker.COMPLETE) {
            this.setText(
                    color.name() + "\n" + type.name()
            );
        }
    }

}
