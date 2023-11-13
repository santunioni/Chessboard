package chess.domain.play;

import chess.domain.assertions.BoardAssertion;
import chess.domain.assertions.ColorThreatensPositionAssertion;
import chess.domain.board.Board;
import chess.domain.board.PieceColor;
import chess.domain.board.PieceType;
import chess.domain.board.ReadonlyBoard;
import chess.domain.grid.Path;
import chess.domain.grid.Position;
import chess.domain.grid.Rank;

public record Castle(PieceColor color, CastleSide castleSide) implements Play {

  public boolean canActOnCurrentState(ReadonlyBoard board) {
    return this.kingAndRookNeverMoved(board)
        && this.kingIsNotChecked(board)
        && this.kingPath().isClearedOn(board)
        && !this.kingPath().isThreatenedBy(this.color.opposite(), board);
  }

  public void actOn(Board board) {
    board.changePosition(this.rookPosition(), this.kingFirstStep());
    board.changePosition(this.kingPosition(), this.kingSecondStep());
  }

  private boolean kingIsNotChecked(ReadonlyBoard board) {
    BoardAssertion kingChecked =
        new ColorThreatensPositionAssertion(this.color.opposite(), this.kingPosition());
    return kingChecked.not().test(board);
  }

  private boolean kingAndRookNeverMoved(ReadonlyBoard board) {
    if (!this.kingIsOnItsInitialPosition(board) || !this.rookIsOnItsInitialPosition(board)) {
      return false;
    }

    for (Play oldPlay : board.history()) {
      PieceColor oldPlayPieceColor = oldPlay.getPlayerColor();
      String oldPlayAlgebraicNotation = oldPlay.toDto().algebraicNotation();
      if (oldPlayPieceColor.equals(this.color) && (oldPlayAlgebraicNotation.contains("K")
          || oldPlayAlgebraicNotation.contains("R" + this.castleSide.toRookFile()))) {
        return false;
      }
    }

    return true;
  }

  private Position kingFirstStep() {
    return this.kingPosition().nextOn(this.castleSide.toDirection()).orElseThrow();
  }

  private Position kingSecondStep() {
    return this.kingFirstStep().nextOn(this.castleSide.toDirection()).orElseThrow();
  }

  private Path kingPath() {
    return new Path(this.kingPosition(), this.castleSide.toDirection(), 2);
  }

  private Position rookPosition() {
    return this.castleSide.equals(CastleSide.KING_SIDE) ? new Position("h" + this.rank()) :
        new Position("a" + this.rank());
  }

  private boolean kingIsOnItsInitialPosition(ReadonlyBoard board) {
    return board.getPieceAt(this.kingPosition(), color, PieceType.KING).isPresent();
  }

  private boolean rookIsOnItsInitialPosition(ReadonlyBoard board) {
    return board.getPieceAt(this.rookPosition(), color, PieceType.ROOK).isPresent();
  }

  private Position kingPosition() {
    return color.kingInitialPosition();
  }

  private Rank rank() {
    return this.kingPosition().rank();
  }

  public PlayDto toDto() {
    return new PlayDto(this.color, this.toLongAlgebraicNotation());
  }

  public String toLongAlgebraicNotation() {
    return this.castleSide.equals(CastleSide.KING_SIDE) ? "0-0" : "0-0-0";
  }
}
