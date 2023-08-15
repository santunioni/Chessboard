package chess.board;

import chess.pieces.*;

public class BoardStateFactory {
    public BoardState createFreshBoardState() {
        var state = new BoardState();
        this.placeRooks(state);
        this.placeKnights(state);
        this.placeBishops(state);
        this.placeQueens(state);
        this.placeKings(state);
        this.placePawns(state);
        return state;
    }

    private void placeRooks(BoardState state) {
        state.putPieceInSquare("a1", new Rook(Color.WHITE));
        state.putPieceInSquare("h1", new Rook(Color.WHITE));
        state.putPieceInSquare("a8", new Rook(Color.BLACK));
        state.putPieceInSquare("h8", new Rook(Color.BLACK));
    }

    private void placeKnights(BoardState state) {
        state.putPieceInSquare("b1", new Knight(Color.WHITE));
        state.putPieceInSquare("g1", new Knight(Color.WHITE));
        state.putPieceInSquare("b8", new Knight(Color.BLACK));
        state.putPieceInSquare("g8", new Knight(Color.BLACK));
    }

    private void placeBishops(BoardState state) {
        state.putPieceInSquare("c1", new Bishop(Color.WHITE));
        state.putPieceInSquare("f1", new Bishop(Color.WHITE));
        state.putPieceInSquare("c8", new Bishop(Color.BLACK));
        state.putPieceInSquare("f8", new Bishop(Color.BLACK));
    }

    private void placeQueens(BoardState state) {
        state.putPieceInSquare("d1", new Queen(Color.WHITE));
        state.putPieceInSquare("d8", new Queen(Color.BLACK));
    }

    private void placeKings(BoardState state) {
        state.putPieceInSquare("e1", new King(Color.WHITE));
        state.putPieceInSquare("e8", new King(Color.BLACK));
    }

    private void placePawns(BoardState state) {
        for (char column = 'a'; column <= 'h'; column++) {
            state.putPieceInSquare(column + "2", new Pawn(Color.WHITE));
            state.putPieceInSquare(column + "7", new Pawn(Color.BLACK));
        }
    }
}
