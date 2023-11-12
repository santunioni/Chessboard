package chess.ui.pieces;

import chess.domain.grid.Position;

public interface PieceUiFactoryOnClickedPieceCallback {
  void call(Position position);
}
