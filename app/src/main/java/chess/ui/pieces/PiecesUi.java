package chess.ui.pieces;

import chess.game.board.GameController;
import chess.game.board.pieces.PieceSpecification;
import chess.game.grid.Position;
import chess.ui.grid.SquaresUi;
import chess.ui.plays.PlaysUi;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PiecesUi extends JPanel {
  private final PlaysUi moves;
  private final SquaresUi grid;
  private final GameController controller;

  public PiecesUi(SquaresUi grid, GameController gameController, PlaysUi moves) {
    super(null); // Null layout for absolute positioning
    this.grid = grid;
    this.controller = gameController;
    this.moves = moves;
    this.setOpaque(false);
  }

  public void repaint() {
    if (this.grid == null || this.controller == null) {
      return;
    }
    this.replacePieces();
  }

  private void replacePieces() {
    this.removeAll();
    for (var position : Position.values()) {
      var pieceOptional = this.controller.getPieceAt(position);
      pieceOptional.ifPresent(piece -> this.add(this.createPieceUiAtPosition(position, piece)));
    }
  }

  private JLabel createPieceUiAtPosition(Position position, PieceSpecification piece) {
    var rectangle = this.grid.getRectangleForPosition(position, 0.8);
    var pieceIconFactory = new PieceIconFactory(rectangle.width);
    var pieceUi = new JLabel();

    pieceUi.setIcon(pieceIconFactory.getIcon(piece.color(), piece.pieceType()));
    pieceUi.setBounds(rectangle);
    pieceUi.addMouseListener(new MouseAdapter() {
      public void mouseClicked(MouseEvent event) {
        if (PiecesUi.this.moves.isHighlighted(position)) {
          PiecesUi.this.moves.unhighlight(position);
        } else {
          PiecesUi.this.moves.highlight(position);
        }
      }
    });

    return pieceUi;
  }
}
