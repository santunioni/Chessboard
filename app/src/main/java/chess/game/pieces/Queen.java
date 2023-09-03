package chess.game.pieces;

import chess.game.grid.BoardPath;
import chess.game.grid.BoardPathDirection;
import chess.game.grid.BoardPathReachabilityAnalyzer;
import chess.game.grid.Position;
import chess.game.plays.Capture;
import chess.game.plays.Move;
import chess.game.plays.Play;

import java.util.ArrayList;
import java.util.List;

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

    public List<Play> getPossiblePlays() {
        var plays = new ArrayList<Play>();

        for (var direction : BoardPathDirection.allDirections()) {
            for (var position : new BoardPath(this.board.getMyPosition(), direction)) {
                plays.add(new Move(this.getColor(), this.board.getMyPosition(), position));
                plays.add(new Capture(this.getColor(), this.board.getMyPosition(), position));
                if (this.board.getPieceAt(position).isPresent()) {
                    break;
                }
            }
        }

        return plays;
    }
}
