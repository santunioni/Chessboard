package chess.game.assertions;

import chess.game.board.ReadonlyBoard;

public interface BoardAssertion {
  boolean test(ReadonlyBoard board);
}
