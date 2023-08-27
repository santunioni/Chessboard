package chess.game.board;

import chess.game.grid.Position;

public interface BoardPlacement extends BoardPieceAtPositionProvider {
    Position getMyPosition();
}
