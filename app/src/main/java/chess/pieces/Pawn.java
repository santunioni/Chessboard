package chess.pieces;

import chess.board.Color;
import chess.board.path.BoardPathDirection;
import chess.board.path.BoardPathWalker;
import chess.plays.Displacement;
import chess.board.position.Position;
import chess.board.position.Rank;

import java.util.HashSet;
import java.util.Set;

public class Pawn implements Piece {

    private final Color color;
    private final Position position;


    public Pawn(Color color, Position position) {
        this.color = color;
        this.position = position;
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
            return this.position.rank() != Rank.TWO;
        } else {
            return this.position.rank() != Rank.SEVEN;
        }
    }

    @Override
    public Set<Displacement> getValidMoves() {
        var moviments = new HashSet<Displacement>();

        var walker = new BoardPathWalker(this.position);

        walker.walk(1, this.getWalkDirection()).ifPresent(w1 -> {
            moviments.add(new Displacement(this.position, w1.getPosition()));
            if (!this.hasAlreadyMoved()) {
                w1.walk(1, this.getWalkDirection()).ifPresent(w2 -> {
                    moviments.add(new Displacement(this.position, w2.getPosition()));
                });
            }
        });

        return moviments;
    }
}
