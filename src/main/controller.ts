import { Players, Player, PlayerSwitcher } from "./player"
import { Board } from "./board"

export class Controller {
  private players: PlayerSwitcher<Player>

  public constructor(players: Players, private board: Board) {
    this.players = new PlayerSwitcher(players.white, players.black)
  }

  public runRound(): void {
    const move = this.players.getCurrentPlayer().getMove()
    this.board.move(move)
    this.players.switch()
  }
}
