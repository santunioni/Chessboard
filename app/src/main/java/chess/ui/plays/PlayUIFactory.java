package chess.ui.plays;

import chess.game.plays.PlayDTO;
import chess.ui.grid.SquaresUI;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PlayUIFactory {

    private final SquaresUI grid;

    public PlayUIFactory(SquaresUI grid) {
        this.grid = grid;
    }

    public JLabel createJLabelForPlay(PlayDTO play, Runnable onPlayedCallback) {
        var target = play.getTo();
        var moveUI = new JLabel();
        moveUI.setBounds(grid.getRectangleForPosition(target, 0.8));
        moveUI.setOpaque(true);
        moveUI.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent event) {
                onPlayedCallback.run();
            }
        });
        return moveUI;
    }
}
