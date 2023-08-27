package chess.ui.pieces;

import chess.game.board.BoardController;
import chess.game.grid.Position;
import chess.game.pieces.PieceProperties;
import chess.ui.grid.SquaresUI;
import chess.ui.plays.PlaysUI;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PiecesUI extends JPanel {
    private final PlaysUI moves;
    private final SquaresUI grid;
    private final BoardController board;

    public PiecesUI(SquaresUI grid, BoardController boardController, PlaysUI moves) {
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
            pieceOptional.ifPresent(piece -> this.add(this.createPieceUIAtPosition(position, piece)));
        }
    }

    private JLabel createPieceUIAtPosition(Position position, PieceProperties piece) {
        var rectangle = this.grid.getRectangleForPosition(position, 0.8);
        var pieceIconFactory = new PieceIconFactory(rectangle.width);
        var pieceUI = new JLabel();

        pieceUI.setIcon(pieceIconFactory.getIcon(piece.getColor(), piece.getType()));
        pieceUI.setBounds(rectangle);
        pieceUI.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent event) {
                if (PiecesUI.this.moves.isHighlighted(position)) {
                    PiecesUI.this.moves.unhighlight(position);
                } else {
                    PiecesUI.this.moves.highlight(position);
                }
            }
        });

        return pieceUI;
    }
}
