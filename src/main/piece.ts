import { Position } from "./position"
import { Board } from "./board"

export class Piece {
  public constructor(private board: Board) {}

  public move(position: Position): void {
    this.board.getPiece(position)
  }
}
