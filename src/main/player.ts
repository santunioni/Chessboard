import { Move } from "./position"
import { Input } from "./input.interface"

export class Player {
  constructor(private input: Input) {}

  getMove(): Move {
    return this.input.getInput()
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

export interface Players {
  white: Player
  black: Player
}
