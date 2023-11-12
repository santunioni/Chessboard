package chess.ui.pieces;

import chess.application.GameController;
import chess.domain.grid.Position;
import javax.swing.JPanel;

public class PiecesUiLayer extends JPanel {
  private final GameController controller;
  private final String boardId;
  private final PieceUiFactory pieceUiFactory;

  public PiecesUiLayer(GameController gameController, String boardId,
                       PieceUiFactory pieceUiFactory) {
    super(null); // Null layout for absolute positioning
    this.controller = gameController;
    this.boardId = boardId;
    this.pieceUiFactory = pieceUiFactory;
    this.setOpaque(false);
  }

  public void repaint() {
    if (this.controller == null) {
      return;
    }
    this.replacePieces();
  }

  private void replacePieces() {
    this.removeAll();
    for (var position : Position.values()) {
      var pieceOptional = this.controller.getPieceAt(this.boardId, position);
      if (pieceOptional.isEmpty()) {
        continue;
      }
      var piece = pieceOptional.get();
      var pieceUi =
          this.pieceUiFactory.createPieceUiAtPosition(position, piece.color(), piece.pieceType());
      this.add(pieceUi);
    }
  }
}
