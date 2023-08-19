package chess;

import chess.board.BoardState;
import chess.board.BoardStateFactory;
import chess.board.position.File;
import chess.board.position.Position;
import chess.board.position.Rank;

import javax.swing.*;

public class GridUI extends JLayeredPane {

    private final BoardState boardState = new BoardStateFactory().createFreshBoardState();

    GridUI() {
        for (var rank : Rank.values()) {
            for (var file : File.values()) {
                var position = new Position(file, rank);
                var square = new SquareUIJButton(position);
                square.addActionListener(e -> {
                    this.requestFocusInWindow();
                    this.click(position);
                });
                square.setBounds(new PositionRectangle(position));
                this.add(square, Integer.valueOf(0));

                var piece = boardState.getPieceAt(position);
                if(piece.isEmpty()) {
                    continue;
                }

                var pieceIcon = PieceIcon.getIcon(piece.get());
                var pieceLabel = new JLabel(pieceIcon);
                pieceLabel.setBounds(new PositionRectangle(position).rescale(0.8));
                pieceLabel.setVisible(true);
                pieceLabel.setOpaque(true);
                pieceLabel.repaint();

                this.add(pieceLabel,  Integer.valueOf(1));
            }
        }
    }

    private void click(Position position) {
        System.out.println("Clicked " + position);
    }
}
