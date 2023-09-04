package chess.ui.grid;

import chess.game.grid.Position;
import java.awt.Color;
import javax.swing.JPanel;

class SquareUI extends JPanel {
  private static final Color lightSquareColor = new Color(240, 217, 181); // Light square color
  private static final Color darkSquareColor = new Color(181, 136, 99);   // Dark square color

  public SquareUI(Position position) {
    this.setBackground((position.rank().ordinal() + position.file().ordinal()) % 2 == 0 ?
        SquareUI.lightSquareColor : SquareUI.darkSquareColor);
  }
}
