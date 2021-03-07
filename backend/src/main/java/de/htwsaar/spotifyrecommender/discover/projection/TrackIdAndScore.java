package de.htwsaar.spotifyrecommender.discover.projection;

import de.htwsaar.spotifyrecommender.util.DeserializerUtils;
import lombok.Value;

@Value
public class TrackIdAndScore {

    String id;

    Double score;

    public TrackIdAndScore(String uri, Double score) {
        this.id = DeserializerUtils.extractIdFromUri(uri);
        this.score = score;
    }
}
