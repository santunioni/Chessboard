package chess.pieces;

import chess.board.path.BoardPathDirection;
import chess.board.path.BoardPathWalker;
import chess.board.position.Rank;
import chess.plays.Displacement;

import java.util.HashSet;
import java.util.Set;

public class Pawn extends Piece {

    public Pawn(Color color) {
        super(color, Type.PAWN);
    }

    private BoardPathDirection getWalkDirection() {
        if (this.getColor() == Color.WHITE) {
            return BoardPathDirection.VERTICAL_UP;
        } else {
            return BoardPathDirection.VERTICAL_DOWN;
        }
    }

    private boolean hasAlreadyMoved() {
        if (this.getColor() == Color.WHITE) {
            return this.board.getMyPosition().rank() != Rank.TWO;
        } else {
            return this.board.getMyPosition().rank() != Rank.SEVEN;
        }
    }

    @Override
    public Set<Displacement> getValidMoves() {
        var moviments = new HashSet<Displacement>();

        var positionAfterFirstStep = new BoardPathWalker(this.board.getMyPosition()).walk(1, this.getWalkDirection()).map(BoardPathWalker::getPosition);
        if(positionAfterFirstStep.isEmpty()) {
            return moviments;
        }

        if (this.board.getPieceAt(positionAfterFirstStep.get()).isEmpty()) {
            moviments.add(new Displacement(this.board.getMyPosition(), positionAfterFirstStep.get()));
        }

        if(this.hasAlreadyMoved()) {
            return moviments;
        }

        var positionAfterSecondStep = new BoardPathWalker(positionAfterFirstStep.get()).walk(1, this.getWalkDirection()).map(BoardPathWalker::getPosition);

        if (positionAfterSecondStep.isPresent() && this.board.getPieceAt(positionAfterSecondStep.get()).isEmpty()) {
            moviments.add(new Displacement(this.board.getMyPosition(), positionAfterSecondStep.get()));
        }

        return moviments;
    }

}
