package chess.domain.plays;

import chess.domain.board.Board;
import chess.domain.board.ReadonlyBoard;
import chess.domain.pieces.Color;

public interface Play {

  boolean canActOnCurrentState(ReadonlyBoard board);

  void actOn(Board board);

  default Color getPlayerColor() {
    return this.toDto().color();
  }

  PlayDto toDto();

  String toLongAlgebraicNotation();

}
