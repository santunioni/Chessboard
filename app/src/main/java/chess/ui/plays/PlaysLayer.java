package chess.ui.plays;

import chess.application.GameController;
import chess.domain.grid.Position;
import javax.swing.JPanel;

public class PlaysLayer extends JPanel {
  private final GameController controller;
  private final PlayComponentFactory playComponentFactory;
  private final String boardId;
  private Position highlighted;

  public PlaysLayer(GameController controller, String boardId,
                    PlayComponentFactory playComponentFactory) {
    super(null); // Null layout for absolute positioning
    this.controller = controller;
    this.boardId = boardId;
    this.setOpaque(false);
    this.playComponentFactory = playComponentFactory;
  }

  private boolean isHighlighted(Position position) {
    return this.highlighted == position;
  }

  private void highlight(Position position) {
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
      this.playComponentFactory.createPlayUiFromDto(play).ifPresent(this::add);
    }
  }

  private void unhighlight(Position position) {
    if (this.isHighlighted(position)) {
      this.unhighlight();
    }
  }

  public void unhighlight() {
    this.removeAll();
    this.highlighted = null;
    this.repaint();
  }

  public void toggleHighlightedPosition(Position position) {
    if (this.isHighlighted(position)) {
      this.unhighlight(position);
    } else {
      this.highlight(position);
    }
  }
}
