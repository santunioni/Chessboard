package chess.pieces;

import chess.board.path.BoardPathDirection;
import chess.board.path.BoardPathReachabilityAnalyzer;
import chess.board.position.Position;

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

}
