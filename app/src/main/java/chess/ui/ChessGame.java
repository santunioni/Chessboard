package chess.ui;


import chess.game.board.GameController;
import chess.ui.grid.SquaresUi;
import chess.ui.pieces.PiecesUi;
import chess.ui.plays.PlaysUi;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;

public class ChessGame extends JFrame {
  private final int boardSize;
  private final JLayeredPane layers = new JLayeredPane();
  private int nextLayerZindex;

  public ChessGame(int boardSize, GameController controller, String boardId) {
    this.boardSize = boardSize;

    var squares = new SquaresUi();
    var moves = new PlaysUi(squares, controller, boardId);
    var pieces = new PiecesUi(squares, controller, moves, boardId);
    moves.addCallbackForMovedPiece(pieces::repaint);

    this.addLayer(squares);
    this.addLayer(pieces);
    this.addLayer(moves);

    this.configureWindow();
  }

  private void addLayer(Component component) {
    var dimension = new Dimension(this.boardSize, this.boardSize);
    component.setMinimumSize(dimension);
    component.setPreferredSize(dimension);
    component.setMaximumSize(dimension);
    component.setBounds(0, 0, this.boardSize, this.boardSize);
    this.layers.add(component, Integer.valueOf(this.nextLayerZindex++));
  }

  private void configureWindow() {
    this.add(layers);
    this.setMinimumSize(new Dimension(this.boardSize, this.boardSize + 25));
    this.setResizable(false);
    this.setLocationRelativeTo(null);
  }
}
