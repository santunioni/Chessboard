package chess.game.plays;

import chess.game.grid.Position;

public interface PlayDTO {

    PlayName getName();

    Position getFrom();

    Position getTo();
}
