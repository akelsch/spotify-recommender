package de.htwsaar.spotifyrecommender.spotify.model.album;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;

@Data
@JsonDeserialize(using = AlbumDeserializer.class)
class Album {

    private String id;

    private String name;

    private String artist;

    private String imageUrl;
}
