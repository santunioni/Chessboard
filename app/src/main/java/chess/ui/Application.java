package chess.ui;

import chess.game.board.BoardInitializer;
import chess.game.board.GameController;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Application {
  public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> {
      ChessGame chessGame = new ChessGame(1024,
          new GameController(new BoardInitializer().placeAll().getBoard()
          ));
      chessGame.setTitle("Chess");
      chessGame.setVisible(true);
      chessGame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    });
  }
}
