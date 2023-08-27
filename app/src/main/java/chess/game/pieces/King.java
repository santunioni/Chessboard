package chess.game.pieces;

import chess.game.grid.Position;

public class King extends Piece {

    public King(Color color) {
        super(color, Type.KING);
    }

    public boolean couldMoveToIfEmpty(Position position) {
        return this.board.getMyPosition().isNeighborTo(position);
    }

}
