package de.htwsaar.spotifyrecommender.discover.projection;

import de.htwsaar.spotifyrecommender.util.DeserializerUtils;
import lombok.Value;

@Value
public class AlbumIdAndScore {

    String id;

    Double score;

    public AlbumIdAndScore(String uri, Double score) {
        this.id = DeserializerUtils.extractIdFromUri(uri);
        this.score = score;
    }
}
