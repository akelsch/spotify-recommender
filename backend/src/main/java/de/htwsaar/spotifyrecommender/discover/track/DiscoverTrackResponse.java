package de.htwsaar.spotifyrecommender.discover.track;

import lombok.Value;

import java.util.List;

@Value
public class DiscoverTrackResponse {

    List<DiscoverTrack> tracks;
}
