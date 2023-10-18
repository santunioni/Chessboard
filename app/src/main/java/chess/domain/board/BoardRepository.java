package chess.domain.board;

import java.util.HashMap;

public class BoardRepository {
  private final HashMap<String, Board> boards = new HashMap<>();

  public BoardInitializer createNewBoardInitializer() {
    return new BoardInitializer();
  }

  public Board getBoard(String id) {
    return this.boards.get(id);
  }

  public void saveBoard(Board board) {
    boards.put(board.getId(), board);
  }
}
