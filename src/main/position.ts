export interface Position {
  row: number
  col: number
}

export interface Move {
  from: Position
  to: Position
}

export const parsePositionAsString = (position: Position) => {
  return `${position.row}${position.col}`
}
