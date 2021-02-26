package de.htwsaar.spotifyrecommender.discover.album;

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
