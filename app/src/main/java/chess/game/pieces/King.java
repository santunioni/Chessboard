package chess.game.pieces;

import chess.game.grid.BoardPathDirection;
import chess.game.grid.Position;
import chess.game.plays.Castle;
import chess.game.plays.Play;

import java.util.HashSet;
import java.util.Set;

import static chess.game.plays.PlayFunctions.collectDirectionalPlays;

public class King extends Piece {

    public King(Color color) {
        super(color, Type.KING);
    }

    public static Position initialPosition(Color color) {
        return new Position(color == Color.WHITE ? "e1" : "e8");
    }

    public boolean couldMoveToIfEmpty(Position position) {
        return this.board.getMyPosition().isNeighborTo(position);
    }


    public King copy() {
        return new King(this.getColor());
    }

    protected Set<Play> getPossiblePlays() {
        var plays = new HashSet<Play>();
        collectDirectionalPlays(this, this.board, BoardPathDirection.allDirections(), plays::add, 1);

        if (this.getColor() == Color.WHITE) {
            plays.add(new Castle(this.getColor(), new Position("a1")));
            plays.add(new Castle(this.getColor(), new Position("h1")));
        } else {
            plays.add(new Castle(this.getColor(), new Position("a8")));
            plays.add(new Castle(this.getColor(), new Position("h8")));
        }

        return plays;
    }
}
