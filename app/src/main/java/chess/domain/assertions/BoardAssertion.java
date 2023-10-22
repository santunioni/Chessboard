package chess.domain.assertions;

import chess.domain.board.ReadonlyBoard;

public interface BoardAssertion {
  boolean test(ReadonlyBoard board);

  default BoardAssertion and(BoardAssertion that) {
    return board -> this.test(board) && that.test(board);
  }
}
