package chess.ui.pieces;

import chess.board.BoardState;
import chess.board.position.Position;
import chess.pieces.Piece;
import chess.ui.grid.SquarePositionUILocationAuthority;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PieceGridUI extends JPanel {
    private final HighlightedPositionAuthority highlightedPositionAuthority;
    private final SquarePositionUILocationAuthority grid;
    private final BoardState boardState;

    public PieceGridUI(SquarePositionUILocationAuthority grid, BoardState boardState, HighlightedPositionAuthority highlightedPositionAuthority) {
        super(null); // Null layout for absolute positioning
        this.grid = grid;
        this.boardState = boardState;
        this.highlightedPositionAuthority = highlightedPositionAuthority;
        this.setOpaque(false);
    }

    public void repaint() {
        if (this.grid == null || this.boardState == null) {
            return;
        }
        this.replacePieces();
    }

    private void replacePieces() {
        this.removeAll();
        for (var position : Position.values()) {
            var pieceOptional = this.boardState.getPieceAt(position);
            pieceOptional.ifPresent(piece -> this.add(this.createPieceUIAtPosition(position, piece)));
        }
    }

    private JLabel createPieceUIAtPosition(Position position, Piece piece) {
        var rectangle = this.grid.getRectangleForPosition(position, 0.8);
        var pieceIconFactory = new PieceIconFactory(rectangle.width);
        var pieceUI = new JLabel();

        pieceUI.setIcon(pieceIconFactory.getIcon(piece.getColor(), piece.getType()));
        pieceUI.setBounds(rectangle);
        pieceUI.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent event) {
                if (PieceGridUI.this.highlightedPositionAuthority.isHighlighted(position)) {
                    PieceGridUI.this.highlightedPositionAuthority.unhighlight(position);
                } else {
                    PieceGridUI.this.highlightedPositionAuthority.highlight(position);
                }
            }
        });

        return pieceUI;
    }
}
