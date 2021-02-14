package de.htwsaar.spotifyrecommender.discover.artist;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;

@Data
@JsonDeserialize(using = DiscoverArtistDeserializer.class)
class DiscoverArtist {

    private String id;

    private String name;

    private String imageUrl;
}
