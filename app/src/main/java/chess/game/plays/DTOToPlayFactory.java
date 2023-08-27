package chess.game.plays;

public class DTOToPlayFactory {
    public static Play createPlayFromDTO(PlayDTO playDTO) throws IlegalPlay {
        PlayName playName = playDTO.getName();
        return switch (playName) {
            case MOVE -> new Move(playDTO.getFrom(), playDTO.getTo());
            case CAPTURE -> new Capture(playDTO.getFrom(), playDTO.getTo());
            default -> throw new IlegalPlay(null, "PlayName not found");
        };
    }
}
