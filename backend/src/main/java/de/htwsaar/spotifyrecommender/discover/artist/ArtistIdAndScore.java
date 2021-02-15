package de.htwsaar.spotifyrecommender.discover.artist;

import de.htwsaar.spotifyrecommender.util.SpotifyUtils;
import lombok.Value;

@Value
public class ArtistIdAndScore {

    String id;

    Double score;

    public ArtistIdAndScore(String uri, Double score) {
        this.id = SpotifyUtils.extractIdFromUri(uri);
        this.score = score;
    }
}
