package chess.game.board;

import static chess.game.pieces.Pawn.getStartRank;

import chess.game.pieces.Bishop;
import chess.game.pieces.Color;
import chess.game.pieces.King;
import chess.game.pieces.Knight;
import chess.game.pieces.Pawn;
import chess.game.pieces.Queen;
import chess.game.pieces.Rook;

public class BoardStateInitializer {

  private final BoardState boardState;

  public BoardStateInitializer() {
    this(new BoardState());
  }

  public BoardStateInitializer(BoardState boardState) {
    this.boardState = boardState;
  }

  public BoardStateInitializer placeAll() {
    return this.placeRooks().placeKnights().placeBishops().placeQueens().placeKings().placePawns();
  }

  public BoardStateInitializer placeRooks() {
    this.boardState.placePiece("a1", new Rook(Color.WHITE));
    this.boardState.placePiece("h1", new Rook(Color.WHITE));
    this.boardState.placePiece("a8", new Rook(Color.BLACK));
    this.boardState.placePiece("h8", new Rook(Color.BLACK));
    return this;
  }

  public BoardStateInitializer placeKnights() {
    this.boardState.placePiece("b1", new Knight(Color.WHITE));
    this.boardState.placePiece("g1", new Knight(Color.WHITE));
    this.boardState.placePiece("b8", new Knight(Color.BLACK));
    this.boardState.placePiece("g8", new Knight(Color.BLACK));
    return this;
  }

  public BoardStateInitializer placeBishops() {
    this.boardState.placePiece("c1", new Bishop(Color.WHITE));
    this.boardState.placePiece("f1", new Bishop(Color.WHITE));
    this.boardState.placePiece("c8", new Bishop(Color.BLACK));
    this.boardState.placePiece("f8", new Bishop(Color.BLACK));
    return this;
  }

  public BoardStateInitializer placeQueens() {
    this.boardState.placePiece(Queen.initialPosition(Color.WHITE), new Queen(Color.WHITE));
    this.boardState.placePiece(Queen.initialPosition(Color.BLACK), new Queen(Color.BLACK));
    return this;
  }

  public BoardStateInitializer placeKings() {
    this.boardState.placePiece(King.initialPosition(Color.WHITE), new King(Color.WHITE));
    this.boardState.placePiece(King.initialPosition(Color.BLACK), new King(Color.BLACK));
    return this;
  }

  public BoardStateInitializer placePawns() {
    for (char column = 'a'; column <= 'h'; column++) {
      this.boardState.placePiece(column + getStartRank(Color.WHITE).toString(),
          new Pawn(Color.WHITE));
      this.boardState.placePiece(column + getStartRank(Color.BLACK).toString(),
          new Pawn(Color.BLACK));
    }
    return this;
  }

  public BoardState getBoardState() {
    return this.boardState;
  }
}
