package chess;


import chess.ui.grid.PositionLocation;
import chess.ui.grid.SquareGridUI;

import javax.swing.*;
import java.awt.*;

public class Application extends JFrame {


    public Application() {
        var layers = new JLayeredPane();

        var grid = new SquareGridUI().resizeTo(PositionLocation.BOARD_SIZE);

        layers.add(grid, Integer.valueOf(0));

        this.add(layers);
        this.setTitle("Chess");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setMinimumSize(new Dimension(PositionLocation.BOARD_SIZE, PositionLocation.BOARD_SIZE + 25));
        this.setResizable(false);
        this.setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Application chessApp = new Application();
            chessApp.setVisible(true);
        });
    }
}