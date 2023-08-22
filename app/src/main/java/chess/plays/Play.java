package chess.plays;

import chess.board.BoardState;

public interface Play {
    void actUpon(BoardState boardState) throws IlegalPlay;
}
