package chess.domain.plays;

import chess.domain.board.Board;
import chess.domain.board.ReadonlyBoard;
import chess.domain.grid.Position;
import chess.domain.pieces.Color;

/**
 * Represents a Capture play.
 * Attacks are differentiated from displacements because Pawns captures differently than they move.
 * The implementation is:
 * - Displacements are only valid to an empty position.
 * - Attacks are only valid when moving to a position occupied by the enemy.
 */
public class Capture extends Play {
  private final Color color;
  private final Position from;
  private final Position to;

  public Capture(
      Color color,
      Position from,
      Position to) {
    super(color);
    this.color = color;
    this.from = from;
    this.to = to;
  }

  protected boolean canActOnCurrentState(ReadonlyBoard board) {
    return board.getPieceAt(from, color).filter(p -> p.threatens(to)).isPresent()
        && board.getPieceAt(to, color.opposite()).isPresent();
  }

  public void actOn(Board board) {
    board.changePosition(from, to);
  }

  public PlayDto toDto() {
    return new PlayDto(PlayName.CAPTURE, this.from, this.to);
  }

  public Position toPosition() {
    return this.to;
  }

  public Position fromPosition() {
    return this.from;
  }
}
