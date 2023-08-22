package chess;


import chess.board.BoardStateFactory;
import chess.ui.grid.SquaresUI;
import chess.ui.pieces.MovesUI;
import chess.ui.pieces.PiecesUI;

import javax.swing.*;
import java.awt.*;

public class Application extends JFrame {
    private final int BOARD_SIZE;
    private final JLayeredPane layers = new JLayeredPane();
    private int nextLayerZIndex = 0;

    public Application(int BOARD_SIZE) {
        this.BOARD_SIZE = BOARD_SIZE;

        var boardState = new BoardStateFactory().createFreshBoardState();
        var squares = new SquaresUI();
        var moves = new MovesUI(squares, boardState);
        var pieces = new PiecesUI(squares, boardState, moves);
        moves.onMovedPiece(pieces::repaint);

        this.addLayer(squares);
        this.addLayer(pieces);
        this.addLayer(moves);

        this.configureWindow();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Application chessApp = new Application(1024);
            chessApp.setVisible(true);
        });
    }

    private void addLayer(Component component) {
        var dimension = new Dimension(this.BOARD_SIZE, this.BOARD_SIZE);
        component.setMinimumSize(dimension);
        component.setPreferredSize(dimension);
        component.setMaximumSize(dimension);
        component.setBounds(0, 0, this.BOARD_SIZE, this.BOARD_SIZE);
        this.layers.add(component, Integer.valueOf(this.nextLayerZIndex++));
    }

    private void configureWindow() {
        this.add(layers);
        this.setTitle("Chess");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setMinimumSize(new Dimension(this.BOARD_SIZE, this.BOARD_SIZE + 25));
        this.setResizable(false);
        this.setLocationRelativeTo(null);
    }
}
