package chess.ui;


import chess.application.GameController;
import chess.domain.play.validation.PlayValidationError;
import chess.ui.grid.GridLayer;
import chess.ui.pieces.PieceComponentFactory;
import chess.ui.pieces.PiecesLayer;
import chess.ui.plays.PlayComponentFactory;
import chess.ui.plays.PlaysLayer;
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

    var gridLayer = new GridLayer();
    var playComponentFactory = new PlayComponentFactory(gridLayer);
    var piecesComponentFactory = new PieceComponentFactory(gridLayer);

    var playsLayer = new PlaysLayer(controller, boardId, playComponentFactory);
    var piecesLayer = new PiecesLayer(controller, boardId, piecesComponentFactory);

    piecesComponentFactory.setSelectedPieceSubscriber(playsLayer::toggleHighlightedPosition);
    playComponentFactory.setSelectedPlaySubscriber(play -> {
      try {
        controller.makePlay(boardId, play);
      } catch (PlayValidationError e) {
        System.out.println(e.getMessage());
      }
      playsLayer.unhighlight();
      piecesLayer.repaint();
    });

    this.addLayer(gridLayer);
    this.addLayer(piecesLayer);
    this.addLayer(playsLayer);

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
