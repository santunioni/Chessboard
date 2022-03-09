import { Displacement } from "./position"

export class Player {
  getInput(): Displacement {
    return { from: { row: 1, col: 1 }, to: { row: 2, col: 2 } }
  }
}

export class PlayerSwitcher<T extends Player> {
  private currentPlayer: T

  public constructor(private playerOne: T, private playerTwo: T) {
    this.currentPlayer = this.playerOne
  }

  public switch(): void {
    if (this.currentPlayer === this.playerOne) {
      this.currentPlayer = this.playerTwo
    } else {
      this.currentPlayer = this.playerOne
    }
  }

  public getCurrentPlayer(): T {
    return this.currentPlayer
  }
}

export interface ChessPlayers {
  white: Player
  black: Player
}
