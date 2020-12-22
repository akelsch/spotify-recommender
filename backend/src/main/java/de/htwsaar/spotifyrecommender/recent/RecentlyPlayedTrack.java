package de.htwsaar.spotifyrecommender.recent;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;

@Data
@JsonDeserialize(using = RecentlyPlayedTrackDeserializer.class)
class RecentlyPlayedTrack {

    private String id;

    private String title;

    private String artist;

    private String album;

    private String imageUrl;

    private String playedAt;
}
