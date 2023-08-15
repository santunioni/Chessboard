package chess.board;

import chess.board.position.Position;

public interface BoardPlacement extends BoardPieceAtPositionProvider {
    Position getMyPosition();
}
