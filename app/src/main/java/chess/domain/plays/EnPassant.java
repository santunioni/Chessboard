package chess.domain.plays;

import chess.domain.board.Board;
import chess.domain.board.ReadonlyBoard;
import chess.domain.grid.Position;
import chess.domain.grid.Rank;
import chess.domain.pieces.Color;
import chess.domain.pieces.PieceType;

public record EnPassant(Color color, Position from, Position to) implements Play {
  public static Rank getEnPassantRankFor(Color color) {
    return color == Color.WHITE ? Rank.FIVE : Rank.FOUR;
  }


  public boolean canActOnCurrentState(ReadonlyBoard board) {
    return from.rank() == EnPassant.getEnPassantRankFor(color)
        && board.getPieceAt(from, color, PieceType.PAWN)
        .map(p -> p.threatens(to)).orElse(false)
        && board.getPieceAt(to).isEmpty()
        && this.hasVictimJumpedTwoSquaresLastRound(board);
  }

  public void actOn(Board board) {
    board.changePosition(this.from, this.to);
    board.removePieceFromSquare(
        this.to.previousOn(this.color.pawnWalkDirection()).orElseThrow());
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
    return new Position(this.to.file(), this.color.opposite().pawnStartRank());
  }

  private Position victimPositionAfterJumpingTwoSquares() {
    return this.victimInitialPosition()
        .nextOn(this.color.opposite().pawnWalkDirection()).orElseThrow()
        .nextOn(this.color.opposite().pawnWalkDirection()).orElseThrow();
  }

  public PlayDto toDto() {
    return new PlayDto(this.color, this.toLongAlgebraicNotation());
  }

  public String toLongAlgebraicNotation() {
    return this.from + "x" + this.to + " e.p.";
  }
}
