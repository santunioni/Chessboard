package chess.board;

import chess.board.position.Position;
import chess.pieces.Color;
import chess.pieces.Piece;
import chess.pieces.PieceProperties;
import chess.pieces.Type;

import java.util.Optional;

/**
 * Made for clients to interact with the chess game.
 */
public class BoardController {
    BoardState boardState;

    public BoardController(BoardState boardState) {
        this.boardState = boardState;
    }

    public Optional<PieceProperties> getPieceAt(Position position) {
        Optional<Piece> pieceOptional = this.boardState.getPieceAt(position);
        if (pieceOptional.isPresent()) {
            var piece = pieceOptional.get();
            return Optional.of(new PieceProperties() {
                public Color getColor() {
                    return piece.getColor();
                }

                public Type getType() {
                    return piece.getType();
                }
            });
        }
        return Optional.empty();
    }
}
