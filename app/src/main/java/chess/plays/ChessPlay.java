package chess.plays;

import chess.board.BoardState;

public interface ChessPlay {
    void actUpon(BoardState boardState) throws IlegalPlay;
}
