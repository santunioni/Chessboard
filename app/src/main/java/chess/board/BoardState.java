package chess.board;

import chess.board.position.Position;
import chess.pieces.LocatedPiece;

import java.util.HashMap;
import java.util.Optional;

public class BoardState {
    private final HashMap<Position, LocatedPiece> board = new HashMap<>();

    public Optional<LocatedPiece> getPieceInSquare(Position position) {
        return Optional.ofNullable(board.get(position));
    }

    public void putPieceInSquare(Position position, LocatedPiece piece) {
        board.put(position, piece);
        piece.placeInBoard(new InMemoryPositionBoardPlacement(position));
    }

    public void putPieceInSquare(String position, LocatedPiece piece) {
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
