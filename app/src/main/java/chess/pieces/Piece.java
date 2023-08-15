package chess.pieces;

import chess.board.position.Position;
import chess.plays.Displacement;

import java.util.Set;

public interface Piece {
    Set<Displacement> getValidMoves();

    default boolean threatens(Position position) {
        return false;
    }

}
