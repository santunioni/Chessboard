package chess.ui.plays;

import chess.domain.plays.PlayDto;
import chess.ui.grid.SquaresUi;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;

public class PlayUiFactory {

  private final SquaresUi grid;

  public PlayUiFactory(SquaresUi grid) {
    this.grid = grid;
  }

  public JLabel createJlabelForPlay(PlayDto play, Runnable onPlayedCallback) {
    var target = play.uiHighlightPosition();
    var moveUi = new JLabel();
    moveUi.setBounds(grid.getRectangleForPosition(target, 0.8));
    moveUi.setOpaque(true);
    moveUi.addMouseListener(new MouseAdapter() {
      public void mouseClicked(MouseEvent event) {
        onPlayedCallback.run();
      }
    });
    return moveUi;
  }
}
