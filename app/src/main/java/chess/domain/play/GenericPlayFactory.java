package chess.domain.play;

import chess.domain.board.PieceColor;
import chess.domain.board.PieceType;
import chess.domain.grid.Position;

public interface GenericPlayFactory<R> {

  R createMove(PieceType type, PieceColor color, Position from, Position to);

  R createCapture(PieceType type, PieceColor color, Position from, Position to);

  R createPromotionAfterMove(PieceColor color, Position from, Position to,
                             PieceType promotedToType);

  R createPromotionAfterCapture(PieceColor color, Position from, Position to,
                                PieceType promotedToType);

  R createCastle(PieceColor color, CastleSide castleSide);

  R createEnPassant(PieceColor color, Position from, Position to);
}
