package chess.board;

import chess.board.position.Position;

import java.util.HashMap;
import java.util.Optional;

public class BoardState {
    private final HashMap<Position, PlaceableInBoard> board = new HashMap<>();

    public Optional<PlaceableInBoard> getPieceAt(Position position) {
        return Optional.ofNullable(board.get(position));
    }

    public Optional<PlaceableInBoard> getPieceAt(String position) {
        return this.getPieceAt(new Position(position));
    }

    public void putPieceInSquare(Position position, PlaceableInBoard piece) {
        board.put(position, piece);
        piece.placeInBoard(new InMemoryPositionBoardPlacement(position));
    }

    public void putPieceInSquare(String position, PlaceableInBoard piece) {
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
