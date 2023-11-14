package chess.ui.pieces;

import chess.domain.board.PieceColor;
import chess.domain.board.PieceType;
import chess.domain.grid.Position;
import chess.ui.grid.GridLayer;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Objects;
import java.util.Optional;
import javax.swing.ImageIcon;
import javax.swing.JLabel;


public class PieceComponentFactory {
  private static final HashMap<String, ImageIcon> iconCache = new HashMap<>();
  private final GridLayer grid;
  private SelectedPieceSubscriber selectedPieceSubscriber = (position) -> {
  };

  public PieceComponentFactory(GridLayer grid) {
    this.grid = grid;
  }

  public void setSelectedPieceListener(SelectedPieceSubscriber selectedPieceSubscriber) {
    this.selectedPieceSubscriber = selectedPieceSubscriber;
  }

  private ImageIcon getIcon(Integer iconSize, PieceColor color, PieceType pieceType) {
    var path = String.format("chess/ui/pieces/%s-%s.png", color.name().toLowerCase(),
        pieceType.name().toLowerCase());
    var cacheKey = iconSize + path;

    var iconOptional = Optional.ofNullable(iconCache.get(cacheKey));
    if (iconOptional.isEmpty()) {
      var icon = new ImageIcon(
          Objects.requireNonNull(PieceComponentFactory.class.getClassLoader().getResource(path)));
      icon = new ImageIcon(
          icon.getImage().getScaledInstance(iconSize, iconSize, Image.SCALE_SMOOTH));
      iconCache.put(cacheKey, icon);
      iconOptional = Optional.of(icon);
    }

    return iconOptional.get();
  }

  public JLabel createPieceUiAtPosition(Position position, PieceColor color, PieceType pieceType) {
    var rectangle = this.grid.getPositionRectangle(position, 0.8);
    var pieceUi = new JLabel();

    pieceUi.setIcon(this.getIcon(rectangle.width, color, pieceType));
    pieceUi.setBounds(rectangle);
    pieceUi.addMouseListener(new MouseAdapter() {
      public void mouseClicked(MouseEvent event) {
        selectedPieceSubscriber.notifySelectedPieceAt(position);
      }
    });

    return pieceUi;
  }
}
