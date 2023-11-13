package chess.ui.pieces;

import chess.domain.grid.Position;

public interface ClickedPieceSubscriber {
  void notifyClickedPieceAt(Position position);
}
