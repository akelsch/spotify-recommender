package de.htwsaar.spotifyrecommender.spotify.model;

import lombok.Data;
import lombok.Value;

import java.util.List;

@Value
public class ItemsResponse {

    List<Item> items;

    @Data
    public static class Item {

        private String uri;
    }
}
