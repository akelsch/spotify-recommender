package de.htwsaar.spotifyrecommender.spotify.model.item;

import lombok.Value;

import java.util.List;

@Value
public class ItemsResponse {

    List<Item> items;

    @Value
    public static class Item {

        String uri;
    }
}
