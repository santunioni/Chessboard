package chess.ui;


import chess.application.GameController;
import chess.domain.plays.validation.PlayValidationError;
import chess.ui.grid.GridUiLayer;
import chess.ui.pieces.PieceUiFactory;
import chess.ui.pieces.PiecesUiLayer;
import chess.ui.plays.PlayUiFactory;
import chess.ui.plays.PlaysUiLayer;
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

    var grid = new GridUiLayer();
    var playUiFactory = new PlayUiFactory(grid);
    var piecesUiFactory = new PieceUiFactory(grid);

    var playsUiLayer = new PlaysUiLayer(controller, boardId, playUiFactory);
    var piecesUiLayer = new PiecesUiLayer(controller, boardId, piecesUiFactory);

    piecesUiFactory.subscribeToSelectedPiece(playsUiLayer::toggleHighlightedPosition);
    playUiFactory.subscribeToSelectedPlay(play -> {
      try {
        controller.makePlay(boardId, play);
      } catch (PlayValidationError e) {
        System.out.println(e.getMessage());
      }
      playsUiLayer.unhighlight();
      piecesUiLayer.repaint();
    });

    this.addLayer(grid);
    this.addLayer(piecesUiLayer);
    this.addLayer(playsUiLayer);

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
