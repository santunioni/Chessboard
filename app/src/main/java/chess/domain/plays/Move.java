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
public class Move extends Play {
  private final PieceType type;
  private final Color color;
  private final Position from;
  private final Position to;

  public Move(PieceType type, Color color, Position from, Position to) {
    super(color);
    this.type = type;
    this.color = color;
    this.from = from;
    this.to = to;
  }

  protected boolean canActOnCurrentState(ReadonlyBoard board) {
    return board.getPieceAt(from, color).filter(p -> p.couldMoveToIfEmpty(to)).isPresent()
        && board.getPieceAt(to).isEmpty();
  }

  public void actOn(Board board) {
    board.changePosition(from, to);
  }

  public PlayDto toDto() {
    return new PlayDto(this.color,
        this.type.toStringAlgebraicNotation() + this.from + this.to,
        this.to);
  }

  public Position toPosition() {
    return this.to;
  }

  public Position fromPosition() {
    return this.from;
  }
}
