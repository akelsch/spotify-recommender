package de.htwsaar.spotifyrecommender.spotify.model.recent;

import lombok.Value;

import java.util.List;

@Value
public class RecentlyPlayedResponse {

    List<Item> items;

    Cursors cursors;
}
