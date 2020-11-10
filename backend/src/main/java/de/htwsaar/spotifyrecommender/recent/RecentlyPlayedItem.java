package de.htwsaar.spotifyrecommender.recent;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;

@Data
@JsonDeserialize(using = SpotifyItemDeserializer.class)
public class RecentlyPlayedItem {

    private String id;

    private String name;

    private String imageUrl;

    private String type;
}
