package chess.domain.board;

import chess.domain.pieces.Color;
import chess.domain.pieces.PieceFactory;
import chess.domain.pieces.PieceType;

public class BoardInitializer {

  private final Board board = new Board();
  private final PieceFactory pieceFactory = new PieceFactory();


  public BoardInitializer placeAll() {
    for (var type : PieceType.values()) {
      for (var color : Color.values()) {
        this.placePiecesOf(color, type);
      }
    }
    return this;
  }

  public void placePiecesOf(Color color, PieceType type) {
    this.pieceFactory.createPiecesOf(color, type)
        .forEach(piece -> this.board.placePiece(piece.getInitialPosition(), piece));
  }

  public BoardInitializer placePiecesOf(PieceType type) {
    this.pieceFactory.createPiecesOf(type)
        .forEach(piece -> this.board.placePiece(piece.getInitialPosition(), piece));
    return this;
  }

  public Board getBoard() {
    return this.board;
  }
}
