package chess;


import chess.ui.grid.SquareGridUI;

import javax.swing.*;
import java.awt.*;

public class Application extends JFrame {


    public Application() {
        var layers = new JLayeredPane();

        var BOARD_SIZE = 1024;

        var grid = new SquareGridUI();
        grid.resizeTo(BOARD_SIZE);
        grid.setIgnoreRepaint(true);

        layers.add(grid, Integer.valueOf(0));

        this.add(layers);
        this.setTitle("Chess");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setMinimumSize(new Dimension(BOARD_SIZE, BOARD_SIZE + 25));
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