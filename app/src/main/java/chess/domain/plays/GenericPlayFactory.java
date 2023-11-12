package chess.domain.plays;

import chess.domain.grid.Position;
import chess.domain.pieces.Color;
import chess.domain.pieces.PieceType;

public interface GenericPlayFactory<R> {

  R createMove(PieceType type, Color color, Position from, Position to);

  R createCapture(PieceType type, Color color, Position from, Position to);

  R createPromotionAfterMove(Color color, Position from, Position to,
                             PieceType promotedToType);

  R createPromotionAfterCapture(Color color, Position from, Position to,
                                PieceType promotedToType);

  R createCastle(Color color, CastleSide castleSide);

  R createEnPassant(Color color, Position from, Position to);
}
