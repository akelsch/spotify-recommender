package de.htwsaar.spotifyrecommender.spotify.model;

import lombok.Data;
import lombok.Value;

import java.util.List;

@Value
public class ItemsTrackResponse {

    List<ItemWithTrack> items;

    @Data
    public static class ItemWithTrack {
        Track track;

        @Data
        public static class Track {
            private String uri;
        }
    }
}
