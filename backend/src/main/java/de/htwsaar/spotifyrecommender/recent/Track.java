package de.htwsaar.spotifyrecommender.recent;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;

@Data
@JsonDeserialize(using = SpotifyItemDeserializer.class)
class Track {

    private String id;

    private String title;

    // List<String>
    private String artist;

    private String album;

    // URI
    private String imageUrl;

    // Instant
    private String playedAt;
}
