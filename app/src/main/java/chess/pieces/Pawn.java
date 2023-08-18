package chess.pieces;

import chess.board.path.BoardPathDirection;
import chess.board.path.BoardPathWalker;
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

    @Override
    public Set<Displacement> getValidMoves() {
        var moviments = new HashSet<Displacement>();

        var positionAfterFirstStep = new BoardPathWalker(this.board.getMyPosition()).walk(1, this.walkDirection).map(BoardPathWalker::getPosition);
        if (positionAfterFirstStep.isEmpty()) {
            return moviments;
        }

        if (this.board.getPieceAt(positionAfterFirstStep.get()).isEmpty()) {
            moviments.add(new Displacement(this.board.getMyPosition(), positionAfterFirstStep.get()));
        }

        if (this.hasAlreadyMoved()) {
            return moviments;
        }

        var positionAfterSecondStep = new BoardPathWalker(positionAfterFirstStep.get()).walk(1, this.walkDirection).map(BoardPathWalker::getPosition);

        if (positionAfterSecondStep.isPresent() && this.board.getPieceAt(positionAfterSecondStep.get()).isEmpty()) {
            moviments.add(new Displacement(this.board.getMyPosition(), positionAfterSecondStep.get()));
        }

        return moviments;
    }

}
