package chess.ui.plays;

import chess.game.board.BoardController;
import chess.game.grid.Position;
import chess.game.plays.IlegalPlay;
import chess.game.plays.PlayDTO;
import chess.ui.grid.SquaresUI;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class PlaysUI extends JPanel {
    private final BoardController board;
    private final List<Runnable> onMovedPieceCallbacks = new ArrayList<>();
    private final PlayUIFactory playUIFactory;
    private Position highlighted = null;

    public PlaysUI(SquaresUI grid, BoardController board) {
        super(null); // Null layout for absolute positioning
        this.board = board;
        this.playUIFactory = new PlayUIFactory(grid);
        this.setOpaque(false);
    }

    public void addCallbackForMovedPiece(Runnable onMovedPieceCallback) {
        this.onMovedPieceCallbacks.add(onMovedPieceCallback);
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
        this.paintPlaysForPosition(position);
        this.repaint();
    }

    private void paintPlaysForPosition(Position position) {
        this.removeAll();
        List<PlayDTO> plays = this.board.getPlaysFor(position);

        for (var play : plays) {
            JLabel playUI = this.playUIFactory.createJLabelForPlay(play, () -> {
                try {
                    board.makePlay(play);
                    unhighlight();
                    for (var callback : onMovedPieceCallbacks) {
                        callback.run();
                    }
                } catch (IlegalPlay e) {
                    System.out.println("Ilegal play");
                }
            });
            this.add(playUI);
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
