package chess.ui.pieces;

import chess.game.board.BoardController;
import chess.game.grid.Position;
import chess.game.pieces.PieceProperties;
import chess.ui.grid.SquaresUi;
import chess.ui.plays.PlaysUi;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PiecesUi extends JPanel {
  private final PlaysUi moves;
  private final SquaresUi grid;
  private final BoardController board;

  public PiecesUi(SquaresUi grid, BoardController boardController, PlaysUi moves) {
    super(null); // Null layout for absolute positioning
    this.grid = grid;
    this.board = boardController;
    this.moves = moves;
    this.setOpaque(false);
  }

  public void repaint() {
    if (this.grid == null || this.board == null) {
      return;
    }
    this.replacePieces();
  }

  private void replacePieces() {
    this.removeAll();
    for (var position : Position.values()) {
      var pieceOptional = this.board.getPieceAt(position);
      pieceOptional.ifPresent(piece -> this.add(this.createPieceUiAtPosition(position, piece)));
    }
  }

  private JLabel createPieceUiAtPosition(Position position, PieceProperties piece) {
    var rectangle = this.grid.getRectangleForPosition(position, 0.8);
    var pieceIconFactory = new PieceIconFactory(rectangle.width);
    var pieceUi = new JLabel();

    pieceUi.setIcon(pieceIconFactory.getIcon(piece.getColor(), piece.getType()));
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
