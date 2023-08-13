package chess.pieces;

import chess.board.*;

import java.util.HashSet;
import java.util.Set;

public class King {
    final PlayerSide playerSide;
    Position position;
    King(PlayerSide playerSide) {
        this.playerSide = playerSide;
        this.position = King.getInitialPosition(playerSide);
    }

    King(PlayerSide playerSide, Position position) {
        this.playerSide = playerSide;
        this.position = position;
    }

    private static Position getInitialPosition(PlayerSide playerSide) {
        if (playerSide == PlayerSide.WHITE) {
            return new Position(File.E, Rank.ONE);
        } else {
            return new Position(File.E, Rank.EIGHT);
        }
    }

    public Position getPosition() {
        return this.position;
    }

    public Set<Movement> getValidMoves() {
        var movements = new HashSet<Movement>();
        movements.add(new Movement("d4", "d5"));
        return movements;
    }
}
