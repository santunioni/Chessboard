package chess.board;

import chess.board.position.Position;
import chess.pieces.Piece;

import java.util.HashMap;
import java.util.Optional;

public class BoardState {
    private final HashMap<Position, Piece> board = new HashMap<>();

    public Optional<Piece> getPieceAt(Position position) {
        return Optional.ofNullable(board.get(position));
    }

    public Optional<Piece> getPieceAt(String position) {
        return this.getPieceAt(new Position(position));
    }

    public void putPieceInSquare(Position position, Piece piece) {
        board.put(position, piece);
        piece.placeInBoard(new InMemoryPositionBoardPlacement(position));
    }

    public void putPieceInSquare(String position, Piece piece) {
        this.putPieceInSquare(new Position(position), piece);
    }

    public void removePieceFromSquare(Position position) {
        if (this.board.containsKey(position)) {
            var piece = this.board.get(position);
            this.board.remove(position);
            piece.placeInBoard(null);
        }
    }
}
