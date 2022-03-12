import { Move, Position } from "./position"
import { Piece } from "./piece"

export class Board {
  private boardData: Map<string, Piece>

  public constructor() {
    this.boardData = new Map()
  }

  public getPiece(position: Position): Piece | null {
    return this.boardData.get(JSON.stringify(position)) || null
  }

  public placePiece(piece: Piece, position: Position): void {
    this.boardData.set(JSON.stringify(position), piece)
  }

  public move(move: Move) {
    const piece = this.getPiece(move.from)
    if (piece === null) throw new EmptyPlaceOnBoard(move.from)
    this.boardData.delete(JSON.stringify(move.from))
    this.placePiece(piece, move.to)
  }

}

export class EmptyPlaceOnBoard extends Error {
  constructor(position: Position) {
    super(`There is no piece in this place: ${position} of the board.`)
  }
}



