package chess.ui.plays;

import chess.domain.grid.Position;
import chess.ui.pieces.PieceUiFactoryOnClickedPieceCallback;

public class DisplayPlaysOnClickedPiecePolicy implements PieceUiFactoryOnClickedPieceCallback {

  private final PlaysUiLayer playsUiLayer;

  public DisplayPlaysOnClickedPiecePolicy(PlaysUiLayer playsUiLayer) {
    this.playsUiLayer = playsUiLayer;
  }

  public void call(Position position) {
    if (playsUiLayer.isHighlighted(position)) {
      playsUiLayer.unhighlight(position);
    } else {
      playsUiLayer.highlight(position);
    }
  }
}
