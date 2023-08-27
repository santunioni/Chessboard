package chess.game.pieces;

import chess.game.grid.BoardPathDirection;
import chess.game.grid.BoardPathReachabilityAnalyzer;
import chess.game.grid.Position;
import chess.game.grid.Rank;

import java.util.Set;

public class Pawn extends Piece {

    private final BoardPathDirection walkDirection;

    public Pawn(Color color) {
        super(color, Type.PAWN);
        this.walkDirection = color == Color.WHITE ? BoardPathDirection.VERTICAL_UP : BoardPathDirection.VERTICAL_DOWN;
    }

    private boolean hasAlreadyMoved() {
        return this.walkDirection == BoardPathDirection.VERTICAL_UP ? this.board.getMyPosition().rank() != Rank.TWO : this.board.getMyPosition().rank() != Rank.SEVEN;
    }

    public boolean couldAttackIfOccupiedByEnemy(Position enemyPosition) {
        var myPosition = this.board.getMyPosition();

        if (Math.abs(myPosition.rank().distanceTo(enemyPosition.rank())) != 1 || Math.abs(myPosition.file().distanceTo(enemyPosition.file())) != 1) {
            return false;
        }

        return myPosition.rank().distanceTo(enemyPosition.rank()) == (this.walkDirection == BoardPathDirection.VERTICAL_UP ? 1 : -1);
    }

    public boolean couldMoveToIfEmpty(Position position) {
        return new BoardPathReachabilityAnalyzer(this.board).isReachableWalkingInOneOfDirections(this.board.getMyPosition(), Set.of(this.walkDirection), position, this.hasAlreadyMoved() ? 1 : 2);
    }
}
