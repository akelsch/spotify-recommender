package de.htwsaar.spotifyrecommender.discover;

import lombok.Value;

import java.util.List;

@Value
class DiscoverResponse {

    List<DiscoverTrack> tracks;
}
