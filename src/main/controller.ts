import { ChessPlayers, Player, PlayerSwitcher } from "./player"

export class Controller {
  private players: PlayerSwitcher<Player>

  public constructor(chessPlayers: ChessPlayers) {
    this.players = new PlayerSwitcher(chessPlayers.white, chessPlayers.black)
  }

  public runRound(): void {
    const displacement = this.players.getCurrentPlayer().getInput()
    this.players.switch()
  }
}
