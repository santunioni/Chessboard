package chess.pieces;

import chess.board.path.BoardPathDirection;
import chess.board.path.BoardPathReachabilityAnalyzer;
import chess.board.position.Position;

public class Bishop extends Piece {


    public Bishop(Color color) {
        super(color, Type.BISHOP);
    }

    public boolean canMoveTo(Position position) {
        return new BoardPathReachabilityAnalyzer(this.board).isReachableWalkingInOneOfDirections(
                this.board.getMyPosition(),
                BoardPathDirection.diagonals(),
                position
        );
    }
}
