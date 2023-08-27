package chess.ui;


import chess.game.board.BoardController;
import chess.ui.grid.SquaresUI;
import chess.ui.pieces.PiecesUI;
import chess.ui.plays.PlaysUI;

import javax.swing.*;
import java.awt.*;

public class ChessGame extends JFrame {
    private final int BOARD_SIZE;
    private final JLayeredPane layers = new JLayeredPane();
    private int nextLayerZIndex = 0;

    public ChessGame(int BOARD_SIZE, BoardController boardController) {
        this.BOARD_SIZE = BOARD_SIZE;

        var squares = new SquaresUI();
        var moves = new PlaysUI(squares, boardController);
        var pieces = new PiecesUI(squares, boardController, moves);
        moves.setOnMovedPieceCallback(pieces::repaint);

        this.addLayer(squares);
        this.addLayer(pieces);
        this.addLayer(moves);

        this.configureWindow();
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
        this.setMinimumSize(new Dimension(this.BOARD_SIZE, this.BOARD_SIZE + 25));
        this.setResizable(false);
        this.setLocationRelativeTo(null);
    }
}
