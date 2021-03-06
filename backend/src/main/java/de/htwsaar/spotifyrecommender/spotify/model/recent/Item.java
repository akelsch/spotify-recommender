package de.htwsaar.spotifyrecommender.spotify.model.recent;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;

@Data
@JsonDeserialize(using = ItemDeserializer.class)
class Item {

    private String id;

    private String title;

    private String artist;

    private String album;

    private String imageUrl;

    private String playedAt;
}
