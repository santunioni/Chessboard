
export class Position {

    public constructor(
        public row: number,
        public col: number
    ) {}

}


export class Movement {

    public constructor(
        public from: Position,
        public to: Position
    ) {
    }
}
