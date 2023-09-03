package chess.game.pieces;

import chess.game.grid.BoardPathDirection;
import chess.game.grid.BoardPathReachabilityAnalyzer;
import chess.game.grid.Position;

public class Queen extends Piece {
    public Queen(Color color) {
        super(color, Type.QUEEN);
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
}
