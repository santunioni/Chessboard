package chess.pieces;

import chess.board.BoardPlacement;
import chess.board.path.BoardPath;
import chess.board.path.BoardPathDirection;
import chess.board.position.Position;
import chess.plays.Displacement;

import java.util.Set;

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

    protected boolean canReachPositionByWalkingInOneOfDirections(Position position, Set<BoardPathDirection> directions) {
        var myPosition = this.board.getMyPosition();
        var direction = myPosition.directionTo(position);

        if (direction.isEmpty() || !directions.contains(direction.get())) {
            return false;
        }

        var path = new BoardPath(myPosition, direction.get());
        for (var step : path) {
            if (step.equals(position)) {
                return true;
            }
            if (this.board.getPieceAt(step).isPresent()) {
                break;
            }
        }

        return false;
    }

    public boolean threatens(Position position) {
        return false;
    }

    abstract public Set<Displacement> getValidMoves();
}
