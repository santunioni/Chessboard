package chess.pieces;

import chess.board.Color;
import chess.board.path.BoardPathDirection;
import chess.board.path.BoardPathWalker;
import chess.board.position.Rank;
import chess.plays.Displacement;

import java.util.HashSet;
import java.util.Set;

public class Pawn implements LocatedPiece {

    private final Color color;
    private BoardPlacement boardPlacement;

    public Pawn(Color color) {
        this.color = color;
    }

    public void placeInBoard(BoardPlacement boardPlacement) {
        this.boardPlacement = boardPlacement;
    }

    private BoardPathDirection getWalkDirection() {
        if (this.color == Color.WHITE) {
            return BoardPathDirection.VERTICAL_UP;
        } else {
            return BoardPathDirection.VERTICAL_DOWN;
        }
    }

    private boolean hasAlreadyMoved() {
        if (this.color == Color.WHITE) {
            return this.boardPlacement.getPositionInBoard().rank() != Rank.TWO;
        } else {
            return this.boardPlacement.getPositionInBoard().rank() != Rank.SEVEN;
        }
    }

    @Override
    public Set<Displacement> getValidMoves() {
        var moviments = new HashSet<Displacement>();

        var walker = new BoardPathWalker(this.boardPlacement.getPositionInBoard());

        walker.walk(1, this.getWalkDirection()).ifPresent(w1 -> {
            moviments.add(new Displacement(this.boardPlacement.getPositionInBoard(), w1.getPosition()));
            if (!this.hasAlreadyMoved()) {
                w1.walk(1, this.getWalkDirection()).ifPresent(w2 -> {
                    moviments.add(new Displacement(this.boardPlacement.getPositionInBoard(), w2.getPosition()));
                });
            }
        });

        return moviments;
    }

}
