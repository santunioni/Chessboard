package chess.domain.play;

import chess.domain.board.Board;
import chess.domain.board.Piece;
import chess.domain.board.PieceColor;
import chess.domain.board.PieceType;
import chess.domain.board.ReadonlyBoard;
import chess.domain.grid.Position;
import chess.domain.grid.Rank;
import com.google.common.collect.ImmutableSet;

public record Promotion(Play playBeforePromotion, PieceColor color, Position from, Position to,
                        PieceType toPieceType) implements Play {
  public static final ImmutableSet<PieceType> possibleTypes =
      ImmutableSet.of(PieceType.ROOK, PieceType.KNIGHT, PieceType.BISHOP, PieceType.QUEEN);


  public Promotion(Move move, PieceType toPieceType) {
    this(move, move.getPlayerColor(), move.from(), move.to(), toPieceType);
  }

  public Promotion(Capture capture, PieceType toPieceType) {
    this(capture, capture.getPlayerColor(), capture.from(), capture.to(), toPieceType);
  }

  private boolean isOnPromotionRank() {
    return this.to.rank().equals(color == PieceColor.WHITE ? Rank.EIGHT : Rank.ONE);
  }

  private boolean isPawn(ReadonlyBoard board) {
    return board.getPieceAt(this.from, this.color, PieceType.PAWN).isPresent();
  }

  public boolean canActOnCurrentState(ReadonlyBoard board) {
    return this.playBeforePromotion.canActOnCurrentState(board)
        && this.isOnPromotionRank()
        && this.isPawn(board)
        && possibleTypes.contains(this.toPieceType);
  }

  public void actOn(Board board) {
    this.playBeforePromotion.actOn(board);
    var pawn = board.getPieceAt(this.to, this.color, PieceType.PAWN).orElseThrow();
    board.placePieceAt(this.to, new Piece(pawn.idInBoard(), this.color, this.toPieceType));
  }

  public PlayDto toDto() {
    return new PlayDto(this.color, this.toLongAlgebraicNotation());
  }

  public String toLongAlgebraicNotation() {
    return this.playBeforePromotion.toLongAlgebraicNotation() + "="
        + this.toPieceType.toStringAlgebraicNotation();
  }
}
