package chess.ui.plays;

import chess.application.GameController;
import chess.domain.grid.Position;
import chess.domain.plays.validation.PlayValidationError;
import chess.ui.grid.SquaresUi;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PlaysUi extends JPanel {
  private final GameController controller;
  private Runnable onMovedPieceCallback = () -> {
  };
  private final PlayUiFactory playUiFactory;
  private final String boardId;
  private Position highlighted;

  public PlaysUi(SquaresUi grid, GameController controller, String boardId) {
    super(null); // Null layout for absolute positioning
    this.controller = controller;
    this.playUiFactory = new PlayUiFactory(grid);
    this.boardId = boardId;
    this.setOpaque(false);
  }

  public void onMovedPiece(Runnable onMovedPieceCallback) {
    this.onMovedPieceCallback = onMovedPieceCallback;
  }

  public boolean isHighlighted(Position position) {
    return this.highlighted == position;
  }

  public void highlight(Position position) {
    if (this.isHighlighted(position)) {
      return;
    }

    this.highlighted = position;
    this.paintPlaysForPosition(position);
    this.repaint();
  }

  private void paintPlaysForPosition(Position position) {
    this.removeAll();
    var plays = this.controller.getPlaysFor(this.boardId, position);

    for (var play : plays) {
      try {
        JLabel playUi = this.playUiFactory.createPlayFromLongAlgebraicNotation(play.color(),
            play.algebraicNotation());
        playUi.addMouseListener(new MouseAdapter() {
          public void mouseClicked(MouseEvent event) {
            try {
              controller.makePlay(PlaysUi.this.boardId, play);
              unhighlight();
              onMovedPieceCallback.run();
            } catch (PlayValidationError e) {
              System.out.println(e.getMessage());
            }
          }
        });
        this.add(playUi);
      } catch (PlayValidationError e) {
        System.out.println(e.getMessage());
      }
    }
  }

  public void unhighlight(Position position) {
    if (this.isHighlighted(position)) {
      this.unhighlight();
    }
  }

  private void unhighlight() {
    this.removeAll();
    this.highlighted = null;
    this.repaint();
  }
}
