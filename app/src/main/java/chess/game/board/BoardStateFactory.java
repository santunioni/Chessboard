package chess.game.board;

import chess.game.pieces.*;

public class BoardStateFactory {

    public BoardState createFreshBoardState() {
        var state = new BoardState();
        return this.createFreshBoardState(state);
    }

    public BoardState createFreshBoardState(BoardState state) {
        this.placeRooks(state);
        this.placeKnights(state);
        this.placeBishops(state);
        this.placeQueens(state);
        this.placeKings(state);
        this.placePawns(state);
        return state;
    }

    private void placeRooks(BoardState state) {
        state.placePiece("a1", new Rook(Color.WHITE));
        state.placePiece("h1", new Rook(Color.WHITE));
        state.placePiece("a8", new Rook(Color.BLACK));
        state.placePiece("h8", new Rook(Color.BLACK));
    }

    private void placeKnights(BoardState state) {
        state.placePiece("b1", new Knight(Color.WHITE));
        state.placePiece("g1", new Knight(Color.WHITE));
        state.placePiece("b8", new Knight(Color.BLACK));
        state.placePiece("g8", new Knight(Color.BLACK));
    }

    private void placeBishops(BoardState state) {
        state.placePiece("c1", new Bishop(Color.WHITE));
        state.placePiece("f1", new Bishop(Color.WHITE));
        state.placePiece("c8", new Bishop(Color.BLACK));
        state.placePiece("f8", new Bishop(Color.BLACK));
    }

    private void placeQueens(BoardState state) {
        state.placePiece("d1", new Queen(Color.WHITE));
        state.placePiece("d8", new Queen(Color.BLACK));
    }

    private void placeKings(BoardState state) {
        state.placePiece("e1", new King(Color.WHITE));
        state.placePiece("e8", new King(Color.BLACK));
    }

    private void placePawns(BoardState state) {
        for (char column = 'a'; column <= 'h'; column++) {
            state.placePiece(column + "2", new Pawn(Color.WHITE));
            state.placePiece(column + "7", new Pawn(Color.BLACK));
        }
    }
}
