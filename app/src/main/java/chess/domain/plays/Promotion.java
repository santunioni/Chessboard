package chess.domain.plays;

import chess.domain.board.Board;
import chess.domain.board.ReadonlyBoard;
import chess.domain.grid.Position;
import chess.domain.pieces.Bishop;
import chess.domain.pieces.Color;
import chess.domain.pieces.Knight;
import chess.domain.pieces.Pawn;
import chess.domain.pieces.PieceType;
import chess.domain.pieces.Queen;
import chess.domain.pieces.Rook;

public class Promotion extends Play {

  private final Play playBeforePromotion;
  private final Color color;
  private final Position from;
  private final Position to;
  private final PieceType toPieceType;

  public Promotion(Move playBeforePromotion, PieceType toPieceType) {
    super(playBeforePromotion.getPlayerColor());
    this.playBeforePromotion = playBeforePromotion;
    this.color = playBeforePromotion.getPlayerColor();
    this.from = playBeforePromotion.fromPosition();
    this.to = playBeforePromotion.toPosition();
    this.toPieceType = toPieceType;
  }

  public Promotion(Capture playBeforePromotion, PieceType toPieceType) {
    super(playBeforePromotion.getPlayerColor());
    this.playBeforePromotion = playBeforePromotion;
    this.color = playBeforePromotion.getPlayerColor();
    this.from = playBeforePromotion.fromPosition();
    this.to = playBeforePromotion.toPosition();
    this.toPieceType = toPieceType;
  }

  private boolean isOnPromotionRank() {
    return this.from.rank().equals(Pawn.getPromotionRankFor(this.color));
  }

  private boolean isPawn(ReadonlyBoard board) {
    return board.getPieceAt(this.from, this.color, PieceType.PAWN).isPresent();
  }

  protected boolean canActOnCurrentState(ReadonlyBoard board) {
    return this.playBeforePromotion.canActOnCurrentState(board)
        && this.isOnPromotionRank()
        && this.isPawn(board);
  }

  public void actOn(Board board) {
    this.playBeforePromotion.actOn(board);
    var pawnOptional = board.getPieceAt(this.to);
    if (pawnOptional.isPresent()) {
      var pawn = pawnOptional.get();
      var newPiece = switch (this.toPieceType) {
        case ROOK -> new Rook(pawn.idInBoard(), this.color);
        case KNIGHT -> new Knight(pawn.idInBoard(), this.color);
        case BISHOP -> new Bishop(pawn.idInBoard(), this.color);
        case QUEEN -> new Queen(pawn.idInBoard(), this.color);
        default -> throw new RuntimeException("Invalid Type at Promotion");
      };
      board.placePiece(this.to, newPiece);
    }
  }

  public PlayDto toDto() {
    return new PlayDto(PlayName.PROMOTION, this.from, this.to);
  }
}
