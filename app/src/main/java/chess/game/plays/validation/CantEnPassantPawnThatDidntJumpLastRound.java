package chess.game.plays.validation;

public class CantEnPassantPawnThatDidntJumpLastRound extends PlayValidationError {
  public CantEnPassantPawnThatDidntJumpLastRound() {
    super("Cant En Passant pawn that did not jump 2 squares last round");
  }
}
