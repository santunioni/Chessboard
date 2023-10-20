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
import java.util.Set;

public class Promotion extends Play {

  public static final Set<PieceType>
      possibleTypes = Set.of(PieceType.ROOK, PieceType.KNIGHT, PieceType.BISHOP, PieceType.QUEEN);
  private final Play playBeforePromotion;
  private final Color color;
  private final Position from;
  private final Position to;
  private final PieceType toPieceType;

  public Promotion(Move move, PieceType toPieceType) {
    super(move.getPlayerColor());
    this.playBeforePromotion = move;
    this.color = move.getPlayerColor();
    this.from = move.fromPosition();
    this.to = move.toPosition();
    this.toPieceType = toPieceType;
  }

  public Promotion(Capture capture, PieceType toPieceType) {
    super(capture.getPlayerColor());
    this.playBeforePromotion = capture;
    this.color = capture.getPlayerColor();
    this.from = capture.fromPosition();
    this.to = capture.toPosition();
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
    return new PlayDto(this.color,
        this.playBeforePromotion.toDto().algebraicNotation()
            + "=" + this.toPieceType.toStringAlgebraicNotation(), this.to);
  }
}
