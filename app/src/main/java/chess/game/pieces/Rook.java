package chess.game.pieces;

import chess.game.grid.BoardPath;
import chess.game.grid.BoardPathDirection;
import chess.game.grid.BoardPathReachabilityAnalyzer;
import chess.game.grid.Position;
import chess.game.plays.Capture;
import chess.game.plays.Move;
import chess.game.plays.Play;

import java.util.HashSet;
import java.util.Set;

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

    public Set<Play> getPossiblePlays() {
        var plays = new HashSet<Play>();

        for (var direction : Rook.pathDirections) {
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
