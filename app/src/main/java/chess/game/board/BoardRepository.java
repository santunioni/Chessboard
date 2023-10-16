package chess.game.board;

import java.util.HashMap;

public class BoardRepository {
  private final HashMap<String, Board> boards = new HashMap<>();

  BoardInitializer createNewBoardInitializer() {
    return new BoardInitializer();
  }

  Board getBoard(String id) {
    return this.boards.get(id);
  }

  void saveBoard(Board board) {
    boards.put(board.getId(), board);
  }
}
