package chess.domain.board;

public class BoardInitializer {

  private final Board board = new Board();
  private final PieceFactory pieceFactory = new PieceFactory();


  public BoardInitializer placeAll() {
    for (var type : PieceType.values()) {
      for (var color : PieceColor.values()) {
        this.placePiecesOf(color, type);
      }
    }
    return this;
  }

  public void placePiecesOf(PieceColor color, PieceType type) {
    this.pieceFactory.createPiecesOf(color, type)
        .forEach(piece -> this.board.placePieceAt(piece.getInitialPosition(), piece));
  }

  public BoardInitializer placePiecesOf(PieceType type) {
    this.pieceFactory.createPiecesOf(type)
        .forEach(piece -> this.board.placePieceAt(piece.getInitialPosition(), piece));
    return this;
  }

  public Board getBoard() {
    return this.board;
  }
}
