package chess.pieces;

import chess.plays.Displacement;

import java.util.Set;

public interface Piece {
    Set<Displacement> getValidMoves();
}
