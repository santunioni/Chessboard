package chess.pieces;

import chess.board.position.Movement;

import java.util.Set;

public interface Piece {
    Set<Movement> getValidMoves();
}
