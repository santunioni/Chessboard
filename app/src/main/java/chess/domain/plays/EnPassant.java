package chess.domain.plays;

import chess.domain.board.Board;
import chess.domain.board.ReadonlyBoard;
import chess.domain.grid.Position;
import chess.domain.pieces.Color;
import chess.domain.pieces.Pawn;
import chess.domain.pieces.PieceType;

public class EnPassant extends Play {

  private final Color color;
  private final Position from;
  private final Position to;

  public EnPassant(Color color, Position from, Position to) {
    super(color);
    this.color = color;
    this.from = from;
    this.to = to;
  }

  public boolean canActOnCurrentState(ReadonlyBoard board) {
    return from.rank() == Pawn.getEnPassantRankFor(color)
        && board.getPieceAt(from, color, PieceType.PAWN)
        .map(p -> p.threatens(to)).orElse(false)
        && board.getPieceAt(to).isEmpty()
        && this.hasVictimJumpedTwoSquaresLastRound(board);
  }

  public void actOn(Board board) {
    board.changePosition(this.from, this.to);
    board.removePieceFromSquare(
        this.to.previousOn(Pawn.walkDirectionFor(this.color)).orElseThrow());
  }

  private boolean hasVictimJumpedTwoSquaresLastRound(ReadonlyBoard board) {
    var victimJumpingTwoSquares = new Move(
        PieceType.PAWN,
        this.color.opposite(),
        this.victimInitialPosition(),
        this.victimPositionAfterJumpingTwoSquares()
    );
    return board.getLastPlay().map(p -> p.equals(victimJumpingTwoSquares)).orElse(false);
  }

  private Position victimInitialPosition() {
    return new Position(this.to.file(), Pawn.getStartRankFor(this.color.opposite()));
  }

  private Position victimPositionAfterJumpingTwoSquares() {
    return this.victimInitialPosition()
        .nextOn(Pawn.walkDirectionFor(this.color.opposite())).orElseThrow()
        .nextOn(Pawn.walkDirectionFor(this.color.opposite())).orElseThrow();
  }

  public PlayDto toDto() {
    return new PlayDto(this.color, this.from + "x" + this.to + " e.p.", this.to);
  }
}
