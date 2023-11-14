package chess.ui.pieces;

import chess.application.GameController;
import chess.domain.grid.Position;
import javax.swing.JPanel;

public class PiecesLayer extends JPanel {
  private final GameController controller;
  private final String boardId;
  private final PieceComponentFactory pieceComponentFactory;

  public PiecesLayer(GameController gameController, String boardId,
                     PieceComponentFactory pieceComponentFactory) {
    super(null); // Null layout for absolute positioning
    this.controller = gameController;
    this.boardId = boardId;
    this.pieceComponentFactory = pieceComponentFactory;
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
          this.pieceComponentFactory.createPieceUiAtPosition(position, piece.color(),
              piece.pieceType());
      this.add(pieceUi);
    }
  }
}
