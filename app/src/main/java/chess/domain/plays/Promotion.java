package chess.domain.plays;

import chess.domain.board.Board;
import chess.domain.board.ReadonlyBoard;
import chess.domain.grid.Position;
import chess.domain.pieces.Bishop;
import chess.domain.pieces.Color;
import chess.domain.pieces.Knight;
import chess.domain.pieces.Pawn;
import chess.domain.pieces.PieceSpecification;
import chess.domain.pieces.PieceType;
import chess.domain.pieces.Queen;
import chess.domain.pieces.Rook;

public class Promotion extends Play {


  private final Color color;
  private final Position from;
  private final PieceType to;

  public Promotion(Color color, Position from, PieceType to) {
    super(color);
    this.color = color;
    this.from = from;
    this.to = to;
  }

  private boolean isOnPromotionRank() {
    return this.from.rank().equals(Pawn.getPromotionRankFor(this.color));
  }

  private Position targetPosition() {
    return this.from.nextOn(Pawn.walkDirectionFor(this.color)).orElseThrow();
  }

  protected boolean canActOnCurrentState(ReadonlyBoard board) {
    return this.isOnPromotionRank()
        && board
        .getPieceAt(this.from, new PieceSpecification(this.color, PieceType.PAWN)).isPresent()
        && board.getPieceAt(this.targetPosition()).isEmpty();
  }

  public void actOn(Board board) {
    var pawn = board.getPieceAt(this.from, new PieceSpecification(this.color, PieceType.PAWN))
        .orElseThrow();
    var newPiece = switch (this.to) {
      case ROOK -> new Rook(pawn.idInBoard(), this.color);
      case KNIGHT -> new Knight(pawn.idInBoard(), this.color);
      case BISHOP -> new Bishop(pawn.idInBoard(), this.color);
      case QUEEN -> new Queen(pawn.idInBoard(), this.color);
      default -> throw new RuntimeException("Invalid Type at Promotion");
    };
    board.removePieceFromSquare(this.from);
    board.placePiece(this.targetPosition(), newPiece);
  }

  public PlayDto toDto() {
    return new PlayDto(PlayName.PROMOTION,
        this.from.previousOn(Pawn.walkDirectionFor(this.color)).orElseThrow(), this.from);
  }
}
