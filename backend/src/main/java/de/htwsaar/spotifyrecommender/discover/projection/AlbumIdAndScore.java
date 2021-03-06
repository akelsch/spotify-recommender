package de.htwsaar.spotifyrecommender.discover.projection;

import de.htwsaar.spotifyrecommender.util.SpotifyUtils;
import lombok.Value;

@Value
public class AlbumIdAndScore {

    String id;

    Double score;

    public AlbumIdAndScore(String uri, Double score) {
        this.id = SpotifyUtils.extractIdFromUri(uri);
        this.score = score;
    }
}
