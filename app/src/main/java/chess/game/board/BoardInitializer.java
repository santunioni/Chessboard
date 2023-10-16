package chess.game.board;

import chess.game.board.pieces.Color;
import chess.game.board.pieces.PieceFactory;
import chess.game.board.pieces.PieceSpecification;
import chess.game.board.pieces.PieceType;

public class BoardInitializer {

  private final Board board = new Board();
  private final PieceFactory pieceFactory = new PieceFactory();


  public BoardInitializer placeAll() {
    for (var pieceType : PieceType.values()) {
      for (var color : Color.values()) {
        this.placePiecesOf(new PieceSpecification(color, pieceType));
      }
    }
    return this;
  }

  private void placePiecesOf(PieceSpecification pieceSpecification) {
    this.pieceFactory.createPiecesOf(pieceSpecification).forEach(this.board::placePiece);
  }

  public Board getBoard() {
    return this.board;
  }
}
