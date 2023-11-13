package chess.ui.plays;

import java.awt.Rectangle;
import javax.swing.JLabel;

public class PlayUi extends JLabel {

  private Runnable onSelectedPlay = () -> {
  };

  PlayUi(Rectangle bounds) {
    super();
    this.setBounds(bounds);
    this.setOpaque(true);
  }

  public void select() {
    this.onSelectedPlay.run();
  }

  public void onSelectedPlay(Runnable cb) {
    this.onSelectedPlay = cb;
  }
}

