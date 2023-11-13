package chess.domain.play;

import chess.domain.board.PieceColor;
import chess.domain.board.PieceType;
import chess.domain.grid.Position;

public class PlayFactory implements GenericPlayFactory<Play> {

  public Move createMove(PieceType type, PieceColor color, Position from, Position to) {
    return new Move(type, color, from, to);
  }

  public Capture createCapture(PieceType type, PieceColor color, Position from, Position to) {
    return new Capture(type, color, from, to);
  }

  public Promotion createPromotionAfterMove(PieceColor color, Position from, Position to,
                                            PieceType promotedToType) {
    return new Promotion(new Move(PieceType.PAWN, color, from, to), promotedToType);
  }

  public Promotion createPromotionAfterCapture(PieceColor color, Position from, Position to,
                                               PieceType promotedToType) {
    return new Promotion(new Capture(PieceType.PAWN, color, from, to), promotedToType);
  }

  public Castle createCastle(PieceColor color, CastleSide castleSide) {
    return new Castle(color, castleSide);
  }

  public EnPassant createEnPassant(PieceColor color, Position from, Position to) {
    return new EnPassant(color, from, to);
  }
}
