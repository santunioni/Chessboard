package chess.game.plays;

import chess.game.grid.Position;

public interface PlayDTO {

    PlayName getName();

    Position getFrom();

    Position getTo();


    default boolean equals(PlayDTO that) {
        return this.getName() == that.getName() && this.getFrom() == that.getFrom() && this.getTo() == that.getTo();
    }

}
