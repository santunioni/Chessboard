package chess.ui.plays;

import chess.game.board.BoardController;
import chess.game.grid.Position;
import chess.game.plays.PlayDto;
import chess.game.plays.validation.PlayValidationError;
import chess.game.rules.validation.IlegalPlay;
import chess.ui.grid.SquaresUi;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PlaysUi extends JPanel {
  private final BoardController board;
  private final List<Runnable> onMovedPieceCallbacks = new ArrayList<>();
  private final PlayUiFactory playUiFactory;
  private Position highlighted;

  public PlaysUi(SquaresUi grid, BoardController board) {
    super(null); // Null layout for absolute positioning
    this.board = board;
    this.playUiFactory = new PlayUiFactory(grid);
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

    System.out.println("Highlighting " + position);
    this.highlighted = position;
    this.paintPlaysForPosition(position);
    this.repaint();
  }

  private void paintPlaysForPosition(Position position) {
    this.removeAll();
    List<PlayDto> plays = this.board.getPlaysFor(position);

    for (var play : plays) {
      JLabel playUi = this.playUiFactory.createJlabelForPlay(play, () -> {
        try {
          board.makePlay(play);
          unhighlight();
          for (var callback : onMovedPieceCallbacks) {
            callback.run();
          }
        } catch (PlayValidationError | IlegalPlay e) {
          System.out.println(e.getMessage());
        }
      });
      this.add(playUi);
    }
  }

  public void unhighlight(Position position) {
    if (this.isHighlighted(position)) {
      System.out.println("Unhighlighting " + position);
      this.unhighlight();
    }
  }

  private void unhighlight() {
    this.removeAll();
    this.highlighted = null;
    this.repaint();
  }
}
