package chess.ui.pieces;

import chess.domain.grid.Position;

public interface SelectedPieceSubscriber {
  void notifySelectedPieceAt(Position position);
}
