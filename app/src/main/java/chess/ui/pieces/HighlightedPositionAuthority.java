package chess.ui.pieces;

import chess.board.position.Position;

public interface HighlightedPositionAuthority {
    void highlight(Position position);

    void unhighlight(Position position);

    boolean isHighlighted(Position position);
}
