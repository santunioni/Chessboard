package chess.game.pieces;

import chess.game.grid.Position;

public class King extends Piece {

    public King(Color color) {
        super(color, Type.KING);
    }

    public static Position initialPosition(Color color) {
        return new Position(color == Color.WHITE ? "e1" : "e8");
    }

    public boolean couldMoveToIfEmpty(Position position) {
        return this.board.getMyPosition().isNeighborTo(position);
    }


    public King copy() {
        return new King(this.getColor());
    }

}
