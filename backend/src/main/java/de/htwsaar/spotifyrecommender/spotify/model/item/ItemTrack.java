package de.htwsaar.spotifyrecommender.spotify.model.item;

import lombok.Value;

@Value
public class ItemTrack {

    Track track;

    @Value
    public static class Track {

        String uri;
    }
}
