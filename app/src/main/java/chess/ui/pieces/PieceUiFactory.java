package chess.ui.pieces;

import chess.domain.grid.Position;
import chess.domain.pieces.Color;
import chess.domain.pieces.PieceType;
import chess.ui.grid.GridUiLayer;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Objects;
import java.util.Optional;
import javax.swing.ImageIcon;
import javax.swing.JLabel;


public class PieceUiFactory {
  private static final HashMap<String, ImageIcon> iconCache = new HashMap<>();
  private final GridUiLayer grid;
  private ClickedPieceSubscriber clickedPieceSubscriber = (position) -> {
  };

  public PieceUiFactory(GridUiLayer grid) {
    this.grid = grid;
  }

  public void subscribeToClickedPiece(ClickedPieceSubscriber clickedPieceSubscriber) {
    this.clickedPieceSubscriber = clickedPieceSubscriber;
  }

  private ImageIcon getIcon(Integer iconSize, Color color, PieceType pieceType) {
    var path = String.format("chess/ui/pieces/%s-%s.png", color.name().toLowerCase(),
        pieceType.name().toLowerCase());
    var cacheKey = iconSize + path;

    var iconOptional = Optional.ofNullable(iconCache.get(cacheKey));
    if (iconOptional.isEmpty()) {
      var icon = new ImageIcon(
          Objects.requireNonNull(PieceUiFactory.class.getClassLoader().getResource(path)));
      icon = new ImageIcon(
          icon.getImage().getScaledInstance(iconSize, iconSize, Image.SCALE_SMOOTH));
      iconCache.put(cacheKey, icon);
      iconOptional = Optional.of(icon);
    }

    return iconOptional.get();
  }

  public JLabel createPieceUiAtPosition(Position position, Color color, PieceType pieceType) {
    var rectangle = this.grid.getPositionRectangle(position, 0.8);
    var pieceUi = new JLabel();

    pieceUi.setIcon(this.getIcon(rectangle.width, color, pieceType));
    pieceUi.setBounds(rectangle);
    pieceUi.addMouseListener(new MouseAdapter() {
      public void mouseClicked(MouseEvent event) {
        clickedPieceSubscriber.notifyClickedPieceAt(position);
      }
    });

    return pieceUi;
  }
}
