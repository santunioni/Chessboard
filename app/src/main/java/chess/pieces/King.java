package chess.pieces;

import chess.board.File;
import chess.board.PlayerSide;
import chess.board.Position;
import chess.board.Rank;

public class King {

    final PlayerSide playerSide;
    Position position;

    King(PlayerSide playerSide) {
        this.playerSide = playerSide;
        if (playerSide == PlayerSide.WHITE) {
            this.position = new Position(File.E, Rank.ONE);
        } else {
            this.position = new Position(File.E, Rank.EIGHT);
        }
    }

    public Position getPosition() {
        return this.position;
    }

}
