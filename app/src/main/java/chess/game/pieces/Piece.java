package chess.game.pieces;

import chess.game.board.BoardPlacement;
import chess.game.grid.Position;

public abstract class Piece implements PieceProperties {

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
        return this.color.opposite() == piece.color;
    }

    public PieceProperties toProperties() {
        return new PieceProperties() {
            public Color getColor() {
                return Piece.this.getColor();
            }

            public Type getType() {
                return Piece.this.getType();
            }
        };
    }
}
