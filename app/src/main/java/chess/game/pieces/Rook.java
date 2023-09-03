package chess.game.pieces;

import chess.game.grid.BoardPathDirection;
import chess.game.grid.BoardPathReachabilityAnalyzer;
import chess.game.grid.Position;
import chess.game.plays.Play;

import java.util.HashSet;
import java.util.Set;

import static chess.game.plays.PlayFunctions.collectDirectionalPlays;

public class Rook extends Piece {
    private static final Set<BoardPathDirection> pathDirections = Set.of(
            BoardPathDirection.VERTICAL_UP,
            BoardPathDirection.VERTICAL_DOWN,
            BoardPathDirection.HORIZONTAL_LEFT,
            BoardPathDirection.HORIZONTAL_RIGHT
    );

    public Rook(Color color) {
        super(color, Type.ROOK);
    }

    public boolean couldMoveToIfEmpty(Position enemyPosition) {
        return new BoardPathReachabilityAnalyzer(this.board).isReachableWalkingInOneOfDirections(
                this.board.getMyPosition(),
                Rook.pathDirections,
                enemyPosition
        );
    }


    public Rook copy() {
        return new Rook(this.getColor());
    }

    protected Set<Play> getPossiblePlays() {
        var plays = new HashSet<Play>();
        collectDirectionalPlays(this, this.board, Rook.pathDirections, plays::add);
        return plays;
    }
}
