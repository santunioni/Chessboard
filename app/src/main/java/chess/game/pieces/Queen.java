package chess.game.pieces;

import chess.game.grid.BoardPathDirection;
import chess.game.grid.BoardPathReachabilityAnalyzer;
import chess.game.grid.Position;
import chess.game.plays.Play;

import java.util.HashSet;
import java.util.Set;

import static chess.game.plays.PlayFunctions.collectDirectionalPlays;

public class Queen extends Piece {

    public Queen(Color color) {
        super(color, Type.QUEEN);
    }

    public static Position initialPosition(Color color) {
        return new Position(color == Color.WHITE ? "d1" : "d8");
    }

    public boolean couldMoveToIfEmpty(Position position) {
        return new BoardPathReachabilityAnalyzer(this.board).isReachableWalkingInOneOfDirections(
                this.board.getMyPosition(),
                BoardPathDirection.allDirections(),
                position
        );
    }

    public Queen copy() {
        return new Queen(this.getColor());
    }

    protected Set<Play> getPossiblePlays() {
        var plays = new HashSet<Play>();
        collectDirectionalPlays(this, this.board, BoardPathDirection.allDirections(), plays::add);
        return plays;
    }
}
