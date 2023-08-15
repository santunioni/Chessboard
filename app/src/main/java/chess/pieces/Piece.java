package chess.pieces;

import chess.board.BoardPlacement;
import chess.board.position.Position;
import chess.plays.Displacement;

import java.util.Set;

public abstract class Piece {

    private final Color color;
    private final Type type;
    private BoardPlacement boardPlacement;

    public Piece(Color color, Type type) {
        this.color = color;
        this.type = type;
    }

    public void placeInBoard(BoardPlacement boardPlacement) {
        this.boardPlacement = boardPlacement;
    }

    protected Position getPosition() {
        return this.boardPlacement.getPositionInBoard();
    }

    public Color getColor() {
        return this.color;
    }

    public Type getType() {
        return this.type;
    }

    abstract public Set<Displacement> getValidMoves();
}
