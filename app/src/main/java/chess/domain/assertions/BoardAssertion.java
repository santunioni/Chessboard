package chess.domain.assertions;

import chess.domain.board.ReadonlyBoard;

public interface BoardAssertion {
  boolean test(ReadonlyBoard board);
}
