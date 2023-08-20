package chess.ui.pieces;

import chess.board.BoardState;
import chess.board.position.Position;
import chess.ui.grid.SquareGridUI;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MovesUI extends JPanel {
    private final BoardState boardState;
    private final SquareGridUI grid;
    private Position highlighted = null;

    private Runnable onMove = null;

    public MovesUI(SquareGridUI grid, BoardState boardState) {
        super(null); // Null layout for absolute positioning
        this.boardState = boardState;
        this.grid = grid;
        this.setOpaque(false);
    }

    public void onMove(Runnable onMove) {
        this.onMove = onMove;
    }

    public boolean isHighlighted(Position position) {
        return this.highlighted == position;
    }

    public void highlight(Position position) {
        if (this.isHighlighted(position)) {
            return;
        }

        System.out.println("Highlighting " + position);
        this.highlighted = position;
        this.paintMovesFor(position);
        this.repaint();
    }

    private void paintMovesFor(Position position) {
        var pieceOptional = this.boardState.getPieceAt(position);
        if (pieceOptional.isEmpty()) {
            return;
        }
        var piece = pieceOptional.get();

        for (var target : Position.values()) {
            if (!piece.canMoveTo(target)) {
                continue;
            }
            var moveUI = new JLabel();
            moveUI.setBounds(this.grid.getRectangleForPosition(target, 0.8));
            moveUI.setOpaque(true);
            moveUI.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent event) {
                    System.out.println("Moving " + position + " to " + target);
                    boardState.removePieceFromSquare(position);
                    boardState.placePiece(target, piece);
                    unhighlight();
                    if (onMove != null) {
                        onMove.run();
                    }
                }
            });
            this.add(moveUI);
        }
    }

    public void unhighlight(Position position) {
        if (this.isHighlighted(position)) {
            System.out.println("Unhighlighting " + position);
            this.unhighlight();
        }
    }

    private void unhighlight() {
        this.removeAll();
        this.highlighted = null;
        this.repaint();
    }
}