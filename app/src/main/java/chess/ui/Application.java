package chess.ui;

import chess.game.board.BoardRepository;
import chess.game.board.GameController;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Application {
  public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> {
      var controller = new GameController(new BoardRepository());
      ChessGame chessGame = new ChessGame(1024, controller, controller.newGame());
      chessGame.setTitle("Chess");
      chessGame.setVisible(true);
      chessGame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    });
  }
}
