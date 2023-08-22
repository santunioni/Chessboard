package chess.pieces;

import chess.board.path.BoardPathDirection;
import chess.board.path.BoardPathIterator;
import chess.board.position.Position;
import chess.board.position.Rank;
import chess.plays.Displacement;

import java.util.HashSet;
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

    public boolean threatens(Position enemyPosition) {
        var myPosition = this.board.getMyPosition();

        if (Math.abs(myPosition.rank().distanceTo(enemyPosition.rank())) != 1 || Math.abs(myPosition.file().distanceTo(enemyPosition.file())) != 1) {
            return false;
        }

        return myPosition.rank().distanceTo(enemyPosition.rank()) == (this.walkDirection == BoardPathDirection.VERTICAL_UP ? 1 : -1);
    }

    public boolean reaches(Position position) {
        return this.getValidMoves().contains(new Displacement(this.board.getMyPosition(), position));
    }

    public Set<Displacement> getValidMoves() {
        var moviments = new HashSet<Displacement>();

        var walker = new BoardPathIterator(this.board.getMyPosition(), this.walkDirection);
        if (!walker.hasNext()) {
            return moviments;
        }
        var positionAfterFirstStep = walker.next();

        if (this.board.getPieceAt(positionAfterFirstStep).isEmpty()) {
            moviments.add(new Displacement(this.board.getMyPosition(), positionAfterFirstStep));
        } else {
            return moviments;
        }

        if (this.hasAlreadyMoved() || !walker.hasNext()) {
            return moviments;
        }
        var positionAfterSecondStep = walker.next();

        if (this.board.getPieceAt(positionAfterSecondStep).isEmpty()) {
            moviments.add(new Displacement(this.board.getMyPosition(), positionAfterSecondStep));
        }

        return moviments;
    }

}
