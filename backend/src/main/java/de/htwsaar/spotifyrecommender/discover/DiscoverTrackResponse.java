package de.htwsaar.spotifyrecommender.discover;

import lombok.Value;

import java.util.List;

@Value
class DiscoverTrackResponse {

    List<DiscoverTrack> tracks;
}
