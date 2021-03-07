package de.htwsaar.spotifyrecommender.discover.projection;

import de.htwsaar.spotifyrecommender.util.DeserializerUtils;
import lombok.Value;

@Value
public class ArtistIdAndScore {

    String id;

    Double score;

    public ArtistIdAndScore(String uri, Double score) {
        this.id = DeserializerUtils.extractIdFromUri(uri);
        this.score = score;
    }
}
