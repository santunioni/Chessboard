package chess.board;

import chess.board.position.Position;
import chess.pieces.Piece;

import java.util.HashMap;
import java.util.Optional;

public class BoardState {
    private final HashMap<Position, Piece> board = new HashMap<>();

    public Optional<Piece> getPiece(Position position) {
        return Optional.ofNullable(board.get(position));
    };
    public void putPiece(Position position, Piece piece) {
        board.put(position, piece);
    };
    public void removePiece(Position position) {
        board.remove(position);
    };
}
