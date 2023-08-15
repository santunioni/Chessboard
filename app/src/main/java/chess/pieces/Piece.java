package chess.pieces;

import chess.board.BoardPlacement;
import chess.board.PlaceableInBoard;
import chess.board.position.Position;
import chess.plays.Displacement;

import java.util.Set;

public abstract class Piece implements PlaceableInBoard {

    protected final Color color;
    private BoardPlacement boardPlacement;

    public Piece(Color color) {
        this.color = color;
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

    abstract public Set<Displacement> getValidMoves();
}
