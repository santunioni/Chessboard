package chess.domain.play;

import chess.domain.board.Board;
import chess.domain.board.Piece;
import chess.domain.board.PieceColor;
import chess.domain.board.PieceType;
import chess.domain.board.ReadonlyBoard;
import chess.domain.grid.Position;
import chess.domain.grid.Rank;
import java.util.Set;

public record Promotion(Play playBeforePromotion, PieceColor color, Position from, Position to,
                        PieceType toPieceType) implements Play {
  public static final Set<PieceType> possibleTypes =
      Set.of(PieceType.ROOK, PieceType.KNIGHT, PieceType.BISHOP, PieceType.QUEEN);


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
    return this.playBeforePromotion.canActOnCurrentState(board) && this.isOnPromotionRank()
        && this.isPawn(board);
  }

  public void actOn(Board board) {
    this.playBeforePromotion.actOn(board);
    var pawn = board.getPieceAt(this.to, this.color, PieceType.PAWN).orElseThrow();
    var newPiece = switch (this.toPieceType) {
      case ROOK -> new Piece(pawn.idInBoard(), this.color, PieceType.ROOK);
      case KNIGHT -> new Piece(pawn.idInBoard(), this.color, PieceType.KNIGHT);
      case BISHOP -> new Piece(pawn.idInBoard(), this.color, PieceType.BISHOP);
      case QUEEN -> new Piece(pawn.idInBoard(), this.color, PieceType.QUEEN);
      default -> throw new RuntimeException("Invalid Type at Promotion");
    };
    board.placePiece(this.to, newPiece);
  }

  public PlayDto toDto() {
    return new PlayDto(this.color, this.toLongAlgebraicNotation());
  }

  public String toLongAlgebraicNotation() {
    return this.playBeforePromotion.toDto().algebraicNotation() + "="
        + this.toPieceType.toStringAlgebraicNotation();
  }
}
