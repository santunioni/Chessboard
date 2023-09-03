package chess.game.pieces;

import chess.game.grid.BoardPath;
import chess.game.grid.BoardPathDirection;
import chess.game.grid.Position;
import chess.game.plays.Capture;
import chess.game.plays.Castle;
import chess.game.plays.Move;
import chess.game.plays.Play;

import java.util.ArrayList;
import java.util.List;

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

    public List<Play> getPossiblePlays() {
        var plays = new ArrayList<Play>();

        for (var direction : BoardPathDirection.allDirections()) {
            for (var position : new BoardPath(this.board.getMyPosition(), direction, 1)) {
                plays.add(new Move(this.getColor(), this.board.getMyPosition(), position));
                plays.add(new Capture(this.getColor(), this.board.getMyPosition(), position));
            }
        }

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
