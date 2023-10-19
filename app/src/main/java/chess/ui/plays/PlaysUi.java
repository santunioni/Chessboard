package chess.ui.plays;

import chess.application.GameController;
import chess.domain.grid.Position;
import chess.domain.plays.validation.PlayValidationError;
import chess.ui.grid.SquaresUi;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PlaysUi extends JPanel {
  private final GameController controller;
  private final List<Runnable> onMovedPieceCallbacks = new ArrayList<>();
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

  public void addCallbackForMovedPiece(Runnable onMovedPieceCallback) {
    this.onMovedPieceCallbacks.add(onMovedPieceCallback);
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
      JLabel playUi = this.playUiFactory.createJlabelForPlay(play, () -> {
        try {
          controller.makePlay(this.boardId, play);
          unhighlight();
          for (var callback : onMovedPieceCallbacks) {
            callback.run();
          }
        } catch (PlayValidationError e) {
          System.out.println(e.getMessage());
        }
      });
      this.add(playUi);
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
