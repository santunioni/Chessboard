package chess.game.board;

import chess.game.grid.Position;
import chess.game.pieces.Color;
import chess.game.pieces.Piece;
import chess.game.pieces.PieceProperties;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class BoardState implements BoardPieceAtPositionProvider {
    private final HashMap<Position, Piece> board = new HashMap<>();

    public Optional<Piece> getPieceAt(Position position) {
        return Optional.ofNullable(board.get(position));
    }

    public void placePiece(Position position, Piece piece) {
        this.removePieceFromSquare(position);
        this.board.put(position, piece);
        piece.placeInBoard(new BoardPlacement() {
            public Position getMyPosition() {
                return position;
            }

            public Optional<Piece> getPieceAt(Position position) {
                return BoardState.this.getPieceAt(position);
            }
        });
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

    public BoardState copy() {
        var newState = new BoardState();
        this.board.forEach(((position, piece) -> newState.placePiece(position, piece.copy())));
        return newState;
    }

    public List<Position> findPositionsWithPiece(PieceProperties piece) {
        var positions = new ArrayList<Position>();
        for (var entry : this.board.entrySet()) {
            if (entry.getValue().isSameTypeAndColor(piece)) {
                positions.add(entry.getKey());
            }
        }
        return positions;
    }

    public List<Piece> getPlayerPieces(Color player) {
        List<Piece> pieces = new ArrayList<>();
        for (var entry : this.board.entrySet()) {
            if (entry.getValue().getColor().equals(player)) {
                pieces.add(entry.getValue());
            }
        }
        return pieces;
    }
}
