package chess.game.board;

import static chess.game.board.pieces.Pawn.getStartRank;

import chess.game.board.pieces.Bishop;
import chess.game.board.pieces.Color;
import chess.game.board.pieces.King;
import chess.game.board.pieces.Knight;
import chess.game.board.pieces.Pawn;
import chess.game.board.pieces.Queen;
import chess.game.board.pieces.Rook;
import chess.game.grid.Position;

public class BoardInitializer {

  private final Board board = new Board();


  public BoardInitializer placeAll() {
    return this.placeRooks().placeKnights().placeBishops().placeQueens().placeKings().placePawns();
  }

  public BoardInitializer placeRooks() {
    this.board.placePiece(new Rook(new Position("a1"), Color.WHITE));
    this.board.placePiece(new Rook(new Position("h1"), Color.WHITE));
    this.board.placePiece(new Rook(new Position("a8"), Color.BLACK));
    this.board.placePiece(new Rook(new Position("h8"), Color.BLACK));
    return this;
  }

  public BoardInitializer placeKnights() {
    this.board.placePiece(new Knight(new Position("b1"), Color.WHITE));
    this.board.placePiece(new Knight(new Position("g1"), Color.WHITE));
    this.board.placePiece(new Knight(new Position("b8"), Color.BLACK));
    this.board.placePiece(new Knight(new Position("g8"), Color.BLACK));
    return this;
  }

  public BoardInitializer placeBishops() {
    this.board.placePiece(new Bishop(new Position("c1"), Color.WHITE));
    this.board.placePiece(new Bishop(new Position("f1"), Color.WHITE));
    this.board.placePiece(new Bishop(new Position("c8"), Color.BLACK));
    this.board.placePiece(new Bishop(new Position("f8"), Color.BLACK));
    return this;
  }

  public BoardInitializer placeQueens() {
    this.board.placePiece(new Queen(Queen.initialPosition(Color.WHITE), Color.WHITE));
    this.board.placePiece(new Queen(Queen.initialPosition(Color.BLACK), Color.BLACK));
    return this;
  }

  public BoardInitializer placeKings() {
    this.board.placePiece(new King(King.initialPosition(Color.WHITE), Color.WHITE));
    this.board.placePiece(new King(King.initialPosition(Color.BLACK), Color.BLACK));
    return this;
  }

  public BoardInitializer placePawns() {
    for (char column = 'a'; column <= 'h'; column++) {
      this.board.placePiece(
          new Pawn(column + getStartRank(Color.WHITE).toString(), Color.WHITE));
      this.board.placePiece(
          new Pawn(column + getStartRank(Color.BLACK).toString(), Color.BLACK));
    }
    return this;
  }

  public Board getBoard() {
    return this.board;
  }
}
