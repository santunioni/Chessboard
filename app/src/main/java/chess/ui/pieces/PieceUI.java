package chess.ui.pieces;

import chess.board.BoardPlacement;
import chess.pieces.Piece;
import chess.ui.grid.PositionRectangle;

import javax.swing.*;
import java.awt.*;

public class PieceUI extends JLabel {
    private final Piece piece;
    private BoardPlacement boardPlacement;

    PieceUI(Piece piece, BoardPlacement boardPlacement) {
        this.piece = piece;
        this.setBoardPlacement(boardPlacement);
        this.init();
    }

    public void setBoardPlacement(BoardPlacement boardPlacement) {
        this.boardPlacement = boardPlacement;
    }

    private void init() {
        var icon = PieceIcon.getIcon(piece);
        this.setIcon(icon);
        if (icon.getImageLoadStatus() != MediaTracker.COMPLETE) {
            this.setText(
                    piece.getColor().name().charAt(0) + piece.getType().name().substring(0, 1)
            );
        }
        this.setBounds(new PositionRectangle(this.boardPlacement.getMyPosition()).rescale(0.8));
    }

}
