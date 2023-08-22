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

    public abstract boolean couldMoveToIfEmpty(Position position);

    public boolean couldAttackIfOccupiedByEnemy(Position position) {
        return this.couldMoveToIfEmpty(position);
    }

    public boolean isEnemyOf(Piece piece) {
        return this.color != piece.color;
    }
}
