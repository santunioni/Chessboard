package chess.domain.play;

import chess.domain.board.Board;
import chess.domain.board.PieceColor;
import chess.domain.board.ReadonlyBoard;

public interface Play {

  boolean canActOnCurrentState(ReadonlyBoard board);

  void actOn(Board board);

  default PieceColor getPlayerColor() {
    return this.toDto().pieceColor();
  }

  PlayDto toDto();

  String toLongAlgebraicNotation();

}
