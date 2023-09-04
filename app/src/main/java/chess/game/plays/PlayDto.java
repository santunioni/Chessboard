package chess.game.plays;

import chess.game.grid.Position;

public interface PlayDto {

  PlayName getName();

  Position getFrom();

  Position getTo();
}
