package chess.domain.plays;

import chess.domain.assertions.IsPositionThreatenedByColorAssertion;
import chess.domain.board.Board;
import chess.domain.board.ReadonlyBoard;
import chess.domain.grid.Direction;
import chess.domain.grid.Path;
import chess.domain.grid.Position;
import chess.domain.grid.Rank;
import chess.domain.pieces.Color;
import chess.domain.pieces.King;
import chess.domain.pieces.PieceType;

public class Castle extends Play {

  private final Color color;
  private final Position to;

  public Castle(Color color, Position to) {
    super(color);
    this.color = color;
    this.to = to;
  }

  protected boolean canActOnCurrentState(ReadonlyBoard board) {
    return this.kingAndRookNeverMoved(board)
        && this.kingIsNotChecked(board)
        && this.kingPath().isClearOn(board)
        && !this.kingPath().isThreatenedBy(this.color.opposite(), board);
  }

  public void actOn(Board board) {
    board.changePosition(this.rookPosition(), this.kingFirstStep());
    board.changePosition(this.kingPosition(), this.kingSecondStep());
  }
  
  private boolean kingIsNotChecked(ReadonlyBoard board) {
    return !new IsPositionThreatenedByColorAssertion(this.color.opposite(),
        this.kingPosition()).test(board);
  }

  private boolean kingAndRookNeverMoved(ReadonlyBoard board) {
    if (!this.kingIsOnItsInitialPosition(board) || !this.rookIsOnItsInitialPosition(board)) {
      return false;
    }

    for (Play oldPlay : board.history()) {
      PlayDto oldPlayDto = oldPlay.toDto();
      if (oldPlayDto.from().equals(this.kingPosition())
          || oldPlayDto.from().equals(this.rookPosition())) {
        return false;
      }
    }

    return true;
  }

  private Position kingFirstStep() {
    return this.kingPosition().nextOn(this.castleDirection()).orElseThrow();
  }

  private Position kingSecondStep() {
    return this.kingFirstStep().nextOn(this.castleDirection()).orElseThrow();
  }

  private Path kingPath() {
    return new Path(this.kingPosition(), this.castleDirection(), 2);
  }

  private Position rookPosition() {
    return this.castleDirection().equals(Direction.HORIZONTAL_RIGHT)
        ? new Position("h" + this.rank()) : new Position("a" + this.rank());
  }

  private boolean kingIsOnItsInitialPosition(ReadonlyBoard board) {
    return board.getPieceAt(this.kingPosition(), color, PieceType.KING)
        .isPresent();
  }

  private boolean rookIsOnItsInitialPosition(ReadonlyBoard board) {
    return board.getPieceAt(this.rookPosition(), color, PieceType.ROOK)
        .isPresent();
  }

  private Direction castleDirection() {
    return this.kingPosition().file().distanceTo(to.file()) > 0 ? Direction.HORIZONTAL_RIGHT :
        Direction.HORIZONTAL_LEFT;
  }

  private Position kingPosition() {
    return King.initialPositionFor(color);
  }

  private Rank rank() {
    return this.kingPosition().rank();
  }

  public PlayDto toDto() {
    return new PlayDto(PlayName.CASTLE, King.initialPositionFor(this.color), this.to);
  }
}
