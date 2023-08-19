package chess;

import chess.pieces.Color;
import chess.pieces.Piece;
import chess.pieces.Type;

import javax.swing.*;
import java.util.HashMap;
import java.util.Optional;

record PieceTypeColorRecord(Type type, Color color) {
}

public class PieceIcon {

    private static final HashMap<PieceTypeColorRecord, ImageIcon> cache = new HashMap<>();

    static ImageIcon getIcon(Piece piece) {
        return getIcon(piece.getType(), piece.getColor());
    }

    static ImageIcon getIcon(PieceTypeColorRecord piece) {
        return getIcon(piece.type(), piece.color());
    }

    static ImageIcon getIcon(Type type, Color color) {
        var pieceTypeColor = new PieceTypeColorRecord(type, color);
        var piece = Optional.ofNullable(cache.get(pieceTypeColor));
        if (piece.isEmpty()) {
            var instance = new ImageIcon(
                    "images/" +
                            color.name().toLowerCase() +
                            "-" +
                            type.name().toLowerCase() +
                            ".png");
            cache.put(pieceTypeColor, instance);
            piece = Optional.of(instance);
        }
        return piece.get();
    }
}
