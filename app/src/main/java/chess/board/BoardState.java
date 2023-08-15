package chess.board;

import chess.board.position.Position;
import chess.pieces.Piece;

import java.util.HashMap;
import java.util.Optional;

public class BoardState implements BoardPieceAtPositionProvider {
    private final HashMap<Position, Piece> board = new HashMap<>();

    public Optional<Piece> getPieceAt(Position position) {
        return Optional.ofNullable(board.get(position));
    }

    public void placePiece(Position position, Piece piece) {
        this.removePieceFromSquare(position);
        this.board.put(position, piece);
        piece.placeInBoard(new InMemoryPositionBoardPlacement(position));
    }

    public void placePiece(String position, Piece piece) {
        this.placePiece(new Position(position), piece);
    }

    public void removePieceFromSquare(Position position) {
        if (this.board.containsKey(position)) {
            var piece = this.board.get(position);
            this.board.remove(position);
            piece.placeInBoard(null);
        }
    }
}
