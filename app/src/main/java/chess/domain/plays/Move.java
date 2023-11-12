package chess.domain.plays;


import chess.domain.board.Board;
import chess.domain.board.ReadonlyBoard;
import chess.domain.grid.Position;
import chess.domain.pieces.Color;
import chess.domain.pieces.PieceType;

/**
 * Represents a Displacement play.
 * Displacements are differentiated from attacks because Pawns attack differently than they move.
 * The implementation is:
 * - Displacements are only valid to an empty position.
 * - Attacks are only valid when moving to a position occupied by the enemy.
 */
public record Move(PieceType type, Color color, Position from, Position to) implements Play {

  public boolean canActOnCurrentState(ReadonlyBoard board) {
    return board.getPieceAt(from, color, type).filter(p -> p.couldMoveToIfEmpty(to)).isPresent()
        && board.getPieceAt(to).isEmpty();
  }

  public void actOn(Board board) {
    board.changePosition(from, to);
  }

  public PlayDto toDto() {
    return new PlayDto(this.color, this.toLongAlgebraicNotation());
  }

  public String toLongAlgebraicNotation() {
    return this.type.toStringAlgebraicNotation() + this.from + this.to;
  }
}
