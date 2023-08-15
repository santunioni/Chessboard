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
            return this.getPosition().rank() != Rank.TWO;
        } else {
            return this.getPosition().rank() != Rank.SEVEN;
        }
    }

    @Override
    public Set<Displacement> getValidMoves() {
        var moviments = new HashSet<Displacement>();

        var walker = new BoardPathWalker(this.getPosition());

        walker.walk(1, this.getWalkDirection()).ifPresent(w1 -> {
            moviments.add(new Displacement(this.getPosition(), w1.getPosition()));
            if (!this.hasAlreadyMoved()) {
                w1.walk(1, this.getWalkDirection()).ifPresent(w2 -> {
                    moviments.add(new Displacement(this.getPosition(), w2.getPosition()));
                });
            }
        });

        return moviments;
    }

}
