package chess.domain.plays;

import chess.domain.grid.Position;
import chess.domain.pieces.Color;
import chess.domain.pieces.PieceType;

public class PlayFactory implements GenericPlayFactory<Play> {

  public Move createMove(PieceType type, Color color, Position from, Position to) {
    return new Move(type, color, from, to);
  }

  public Capture createCapture(PieceType type, Color color, Position from, Position to) {
    return new Capture(type, color, from, to);
  }

  public Promotion createPromotionAfterMove(Color color, Position from, Position to,
                                            PieceType promotedToType) {
    return new Promotion(new Move(PieceType.PAWN, color, from, to), promotedToType);
  }

  public Promotion createPromotionAfterCapture(Color color, Position from, Position to,
                                               PieceType promotedToType) {
    return new Promotion(new Capture(PieceType.PAWN, color, from, to), promotedToType);
  }

  public Castle createCastle(Color color, CastleSide castleSide) {
    return new Castle(color, castleSide);
  }

  public EnPassant createEnPassant(Color color, Position from, Position to) {
    return new EnPassant(color, from, to);
  }
}
