package de.htwsaar.spotifyrecommender.spotify.model.track;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;

@Data
@JsonDeserialize(using = TrackDeserializer.class)
class Track {

    private String id;

    private String title;

    private String artist;

    private String album;

    private String imageUrl;
}
