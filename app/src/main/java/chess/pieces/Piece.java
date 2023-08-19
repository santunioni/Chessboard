package chess.pieces;

import chess.board.BoardPlacement;
import chess.board.position.Position;

public abstract class Piece {

    private final Color color;
    private final Type type;
    protected BoardPlacement board;

    public Piece(Color color, Type type) {
        this.color = color;
        this.type = type;
    }

    public void placeInBoard(BoardPlacement boardPlacement) {
        this.board = boardPlacement;
    }

    public Color getColor() {
        return this.color;
    }

    public Type getType() {
        return this.type;
    }

    protected boolean isAllyOf(Piece piece) {
        return this.getColor() == piece.getColor();
    }

    protected boolean isEnemyOf(Piece piece) {
        return !this.isAllyOf(piece);
    }


    public abstract boolean canMoveTo(Position position);

    public boolean threatens(Position position) {
        return this.canMoveTo(position);
    }

}
