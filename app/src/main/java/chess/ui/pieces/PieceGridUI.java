package chess.ui.pieces;

import chess.board.BoardState;
import chess.board.position.Position;
import chess.pieces.Piece;
import chess.ui.grid.SquareGridUI;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PieceGridUI extends JPanel {
    private final MovesUI movesUI;
    private final SquareGridUI grid;
    private final BoardState board;

    public PieceGridUI(SquareGridUI grid, BoardState boardState, MovesUI movesUI) {
        super(null); // Null layout for absolute positioning
        this.grid = grid;
        this.board = boardState;
        this.movesUI = movesUI;
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
                if (PieceGridUI.this.movesUI.isHighlighted(position)) {
                    PieceGridUI.this.movesUI.unhighlight(position);
                } else {
                    PieceGridUI.this.movesUI.highlight(position);
                }
            }
        });

        return pieceUI;
    }
}
