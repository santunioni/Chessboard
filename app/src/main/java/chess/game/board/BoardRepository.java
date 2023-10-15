package chess.game.board;

public class BoardRepository {
  BoardInitializer createNewBoardInitializer() {
    return new BoardInitializer();
  }

  Board getBoard(String id) {
    return createNewBoardInitializer().getBoard();
  }

  void save(Board board) {
  }
}
