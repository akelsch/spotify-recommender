package de.htwsaar.spotifyrecommender.spotify.model.item;

import lombok.Value;

import java.util.List;

@Value
public class ItemsTrackResponse {

    List<ItemTrack> items;

}
