package chess.ui.plays;

import chess.game.board.BoardController;
import chess.game.grid.Position;
import chess.game.plays.IlegalPlay;
import chess.game.plays.PlayDTO;
import chess.ui.grid.SquaresUI;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class PlaysUI extends JPanel {
    private final BoardController board;
    private final SquaresUI grid;
    private Position highlighted = null;

    private Runnable onMovedPieceCallback = () -> {
    };

    public PlaysUI(SquaresUI grid, BoardController board) {
        super(null); // Null layout for absolute positioning
        this.board = board;
        this.grid = grid;
        this.setOpaque(false);
    }

    public void setOnMovedPieceCallback(Runnable onMovedPieceCallback) {
        this.onMovedPieceCallback = onMovedPieceCallback;
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
        this.removeAll();
        List<PlayDTO> plays = this.board.getPlaysFor(position);

        for (var play : plays) {
            var moveUI = new JLabel();
            var target = play.getTo();
            moveUI.setBounds(this.grid.getRectangleForPosition(target, 0.8));
            moveUI.setOpaque(true);
            moveUI.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent event) {
                    try {
                        board.makePlay(play);
                        unhighlight();
                        onMovedPieceCallback.run();
                    } catch (IlegalPlay e) {
                        System.out.println("Ilegal play");
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
