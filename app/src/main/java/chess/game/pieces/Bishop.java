package chess.game.pieces;

import chess.game.grid.BoardPathDirection;
import chess.game.grid.BoardPathReachabilityAnalyzer;
import chess.game.grid.Position;

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
}
