package de.htwsaar.spotifyrecommender.spotify.model;

import lombok.Value;

import java.util.List;

@Value
public class ItemsResponse {

    List<Item> items;
}
