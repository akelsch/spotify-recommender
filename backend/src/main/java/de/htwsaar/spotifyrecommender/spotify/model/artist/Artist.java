package de.htwsaar.spotifyrecommender.spotify.model.artist;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;

@Data
@JsonDeserialize(using = ArtistDeserializer.class)
class Artist {

    private String id;

    private String name;

    private String imageUrl;
}
