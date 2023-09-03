package chess.game.pieces;

import chess.game.grid.*;
import chess.game.plays.Capture;
import chess.game.plays.Move;
import chess.game.plays.Play;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Pawn extends Piece {

    private final BoardPathDirection walkDirection;

    public Pawn(Color color) {
        super(color, Type.PAWN);
        this.walkDirection = color == Color.WHITE ? BoardPathDirection.VERTICAL_UP : BoardPathDirection.VERTICAL_DOWN;
    }

    private boolean hasAlreadyMoved() {
        return this.walkDirection == BoardPathDirection.VERTICAL_UP ? this.board.getMyPosition().rank() != Rank.TWO : this.board.getMyPosition().rank() != Rank.SEVEN;
    }

    public boolean couldCaptureEnemyAt(Position enemyPosition) {
        var myPosition = this.board.getMyPosition();

        if (Math.abs(myPosition.rank().distanceTo(enemyPosition.rank())) != 1 || Math.abs(myPosition.file().distanceTo(enemyPosition.file())) != 1) {
            return false;
        }

        return myPosition.rank().distanceTo(enemyPosition.rank()) == (this.walkDirection == BoardPathDirection.VERTICAL_UP ? 1 : -1);
    }

    public boolean couldMoveToIfEmpty(Position position) {
        return new BoardPathReachabilityAnalyzer(this.board).isReachableWalkingInOneOfDirections(this.board.getMyPosition(), Set.of(this.walkDirection), position, this.hasAlreadyMoved() ? 1 : 2);
    }


    public Pawn copy() {
        return new Pawn(this.getColor());
    }

    public Set<Play> getPossiblePlays() {
        var plays = new HashSet<Play>();

        var from = this.board.getMyPosition();
        for (var verticalDisplacedPosition : new BoardPath(from, this.walkDirection, 2)) {
            for (var horizontalDiff : List.of(-1, 0, 1)) {
                var fileOptional = File.createFromIndex(verticalDisplacedPosition.file().ordinal() + horizontalDiff);
                var targetOptional = fileOptional.map(f -> new Position(f, verticalDisplacedPosition.rank()));
                if (targetOptional.isEmpty()) {
                    continue;
                }
                var target = targetOptional.get();

                if (this.couldMoveToIfEmpty(target)) {
                    plays.add(new Move(this.getColor(), from, target));
                }

                if (this.couldCaptureEnemyAt(target)) {
                    plays.add(new Capture(this.getColor(), from, target));
                }
            }
        }

        return plays;

    }
}
