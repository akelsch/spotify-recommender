package de.htwsaar.spotifyrecommender.discover;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;

@Data
@JsonDeserialize(using = DiscoverAlbumDeserializer.class)
public class DiscoverAlbum {
    private String id;

    private String name;

    private String artist;

    private String imageUrl;
}
