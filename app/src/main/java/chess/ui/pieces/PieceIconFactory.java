package chess.ui.pieces;

import chess.domain.pieces.Color;
import chess.domain.pieces.PieceType;
import java.awt.Image;
import java.util.HashMap;
import java.util.Objects;
import java.util.Optional;
import javax.swing.ImageIcon;


public class PieceIconFactory {
  private static final HashMap<String, ImageIcon> cache = new HashMap<>();
  private final int size;

  PieceIconFactory(int size) {
    this.size = size;
  }

  public ImageIcon getIcon(Color color, PieceType pieceType) {
    var path = "chess/ui/pieces/"
        + color.name().toLowerCase()
        + "-"
        + pieceType.name().toLowerCase()
        + ".png";
    var cacheKey = this.size + path;

    var iconOptional = Optional.ofNullable(cache.get(cacheKey));
    if (iconOptional.isEmpty()) {
      var icon = new ImageIcon(
          Objects.requireNonNull(PieceIconFactory.class.getClassLoader().getResource(path)));
      icon = new ImageIcon(
          icon.getImage().getScaledInstance(this.size, this.size, Image.SCALE_SMOOTH));
      cache.put(cacheKey, icon);
      iconOptional = Optional.of(icon);
    }

    return iconOptional.get();
  }
}
