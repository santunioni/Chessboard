package chess.game.pieces;

import chess.game.grid.BoardPathDirection;
import chess.game.grid.BoardPathReachabilityAnalyzer;
import chess.game.grid.Position;
import chess.game.plays.Play;

import java.util.HashSet;
import java.util.Set;

import static chess.game.plays.PlayFunctions.collectDirectionalPlays;

public class Bishop extends Piece {


    public Bishop(Color color) {
        super(color, Type.BISHOP);
    }

    public boolean couldMoveToIfEmpty(Position position) {
        return new BoardPathReachabilityAnalyzer(this.board).isReachableWalkingInOneOfDirections(
                this.board.getMyPosition(),
                BoardPathDirection.diagonals(),
                position
        );
    }


    public Bishop copy() {
        return new Bishop(this.getColor());
    }

    protected Set<Play> getPossiblePlays() {
        var plays = new HashSet<Play>();
        collectDirectionalPlays(this, this.board, BoardPathDirection.diagonals(), plays::add);
        return plays;
    }
}
