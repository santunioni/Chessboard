package chess.domain.play;

import chess.domain.board.Board;
import chess.domain.board.PieceColor;
import chess.domain.board.PieceType;
import chess.domain.board.ReadonlyBoard;
import chess.domain.grid.Position;

/**
 * Represents a Capture play.
 * Attacks are differentiated from displacements because Pawns captures differently than they move.
 * The implementation is:
 * - Displacements are only valid to an empty position.
 * - Attacks are only valid when moving to a position occupied by the enemy.
 */
public record Capture(PieceType type, PieceColor color, Position from, Position to)
    implements Play {


  public boolean canActOnCurrentState(ReadonlyBoard board) {
    return board.getPieceAt(from, color, type).filter(p -> p.threatens(to)).isPresent()
        && board.getPieceAt(to, color.opposite()).isPresent();
  }

  public void actOn(Board board) {
    board.changePosition(from, to);
  }

  public PlayDto toDto() {
    return new PlayDto(this.color, this.toLongAlgebraicNotation());
  }

  public String toLongAlgebraicNotation() {
    return this.type.toStringAlgebraicNotation() + this.from + "x" + this.to;
  }
}
