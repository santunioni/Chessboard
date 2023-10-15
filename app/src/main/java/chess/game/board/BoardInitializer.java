package chess.game.board;

import static chess.game.board.pieces.Pawn.getStartRank;

import chess.game.board.pieces.Bishop;
import chess.game.board.pieces.Color;
import chess.game.board.pieces.King;
import chess.game.board.pieces.Knight;
import chess.game.board.pieces.Pawn;
import chess.game.board.pieces.Queen;
import chess.game.board.pieces.Rook;

public class BoardInitializer {

  private final Board board = new Board();


  public BoardInitializer placeAll() {
    return this.placeRooks().placeKnights().placeBishops().placeQueens().placeKings().placePawns();
  }

  public BoardInitializer placeRooks() {
    this.board.placePiece("a1", new Rook(Color.WHITE));
    this.board.placePiece("h1", new Rook(Color.WHITE));
    this.board.placePiece("a8", new Rook(Color.BLACK));
    this.board.placePiece("h8", new Rook(Color.BLACK));
    return this;
  }

  public BoardInitializer placeKnights() {
    this.board.placePiece("b1", new Knight(Color.WHITE));
    this.board.placePiece("g1", new Knight(Color.WHITE));
    this.board.placePiece("b8", new Knight(Color.BLACK));
    this.board.placePiece("g8", new Knight(Color.BLACK));
    return this;
  }

  public BoardInitializer placeBishops() {
    this.board.placePiece("c1", new Bishop(Color.WHITE));
    this.board.placePiece("f1", new Bishop(Color.WHITE));
    this.board.placePiece("c8", new Bishop(Color.BLACK));
    this.board.placePiece("f8", new Bishop(Color.BLACK));
    return this;
  }

  public BoardInitializer placeQueens() {
    this.board.placePiece(Queen.initialPosition(Color.WHITE), new Queen(Color.WHITE));
    this.board.placePiece(Queen.initialPosition(Color.BLACK), new Queen(Color.BLACK));
    return this;
  }

  public BoardInitializer placeKings() {
    this.board.placePiece(King.initialPosition(Color.WHITE), new King(Color.WHITE));
    this.board.placePiece(King.initialPosition(Color.BLACK), new King(Color.BLACK));
    return this;
  }

  public BoardInitializer placePawns() {
    for (char column = 'a'; column <= 'h'; column++) {
      this.board.placePiece(column + getStartRank(Color.WHITE).toString(),
          new Pawn(Color.WHITE));
      this.board.placePiece(column + getStartRank(Color.BLACK).toString(),
          new Pawn(Color.BLACK));
    }
    return this;
  }

  public Board getBoard() {
    return this.board;
  }
}
