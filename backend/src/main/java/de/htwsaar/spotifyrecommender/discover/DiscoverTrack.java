package de.htwsaar.spotifyrecommender.discover;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;

@Data
@JsonDeserialize(using = DiscoverTrackDeserializer.class)
class DiscoverTrack {

    private String id;

    private String title;

    private String artist;

    private String album;

    private String imageUrl;
}
